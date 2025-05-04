package managers;

import math.components.Point;
import math.components.Point2;
import static math.util.Utilities.fixCoords;

import math.components.Vector;
import objects.entities.Player;
import org.lwjgl.glfw.GLFW;

public class InputManager {
    Point2 mousePreviousPosition;
    private GameManager gm;
    public boolean pressingW=false, pressingA=false, pressingS=false, pressingD=false,
            pressingRC=false, pressingLC=false; //RightClick, LeftClick
    public InputManager() {
        this.mousePreviousPosition = getMousePosition();
        gm = GameManager.getInstance();
        setUpListeners();
    }

    private void setUpListeners(){
        long window = GameManager.getInstance().renderer.getWindow();
        gm.player.movementVector = gm.cam.vectorALaCam.normalize().multiply(gm.player.speedRate);
        gm.player.movementVector.y = 0;
        //Listener teclas presionadas
        GLFW.glfwSetKeyCallback(window, (windowHandle, key, scancode, action, mods) -> {
            if (action == GLFW.GLFW_PRESS){
                switch (key){
                    case GLFW.GLFW_KEY_W:
                        pressingW = true;
                        break;
                    case GLFW.GLFW_KEY_A:
                        pressingA = true;
                        break;
                    case GLFW.GLFW_KEY_S:
                        pressingS = true;
                        break;
                    case GLFW.GLFW_KEY_D:
                        pressingD = true;
                        break;
                    case GLFW.GLFW_KEY_ESCAPE:
                        break;
                }
            }
            if (action == GLFW.GLFW_RELEASE){
                switch (key){
                    case GLFW.GLFW_KEY_W:
                        pressingW = false;
                        break;
                    case GLFW.GLFW_KEY_A:
                        pressingA = false;
                        break;
                    case GLFW.GLFW_KEY_S:
                        pressingS = false;
                        break;
                    case GLFW.GLFW_KEY_D:
                        pressingD = false;
                        break;
                }
            }
        });
        //Listener botones del ratón
        GLFW.glfwSetMouseButtonCallback(window, (windowHandle, button, action, mods) -> {
            if (action == GLFW.GLFW_PRESS) {
                if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {pressingLC = true;} //Click izq
                else if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {pressingRC = true;} //Click drch
            }
            if (action == GLFW.GLFW_RELEASE) {
                if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {pressingLC = false;} //Click izq
                else if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {pressingRC = false;} //Click drch
            }
        });
        //Listener rueda del ratón
        GLFW.glfwSetScrollCallback(window, (windowHandle, xoffset, yoffset) -> {
            if (yoffset > 0) {
                //Subir rueda del raton
            }
            else if (yoffset < 0) {
                //Bajar rueda del ratón
            }
        });
    }

    public void update() {
        checkInputs();
        checkCamInputs();
    }

    public void checkInputs(){
        Vector movementVector;
        if(pressingW){
            System.out.print("Me muevo de " + gm.player.position);
            movementVector = new Vector(gm.player.movementVector.x, gm.player.movementVector.y, gm.player.movementVector.z);
            gm.player.position = gm.player.position.add(movementVector);
            gm.cam.addPosition(movementVector);
            System.out.println(" a " + gm.player.position);
        }
        if(pressingA){
            System.out.print("Me muevo de " + gm.player.position);
            //Perpendicular izquierda del vector
            movementVector = new Vector(gm.player.movementVector.z*-1, gm.player.movementVector.y, gm.player.movementVector.x);
            System.out.println(movementVector);
            gm.player.position = gm.player.position.add(movementVector);
            gm.cam.addPosition(movementVector);
            System.out.println(" a " + gm.player.position);
        }
        if(pressingS){
            System.out.print("Me muevo de " + gm.player.position);
            movementVector = new Vector(gm.player.movementVector.x, gm.player.movementVector.y, gm.player.movementVector.z);
            gm.player.position = gm.player.position.subtract(movementVector) ;
            gm.cam.substractPosition(movementVector);
            System.out.println(" a " + gm.player.position);
        }
        if(pressingD){
            System.out.print("Me muevo de " + gm.player.position);
            //Perpendicular derecha del vector
            movementVector = new Vector(gm.player.movementVector.z, gm.player.movementVector.y, gm.player.movementVector.x*-1);
            gm.player.position = gm.player.position.add(movementVector);
            gm.cam.addPosition(movementVector);
            System.out.println(" a " + gm.player.position);
        }
        if(pressingRC){
            //Código que se ejecutará mientras se pulse el click derecho
        }
        if(pressingLC){
            //Código que se ejecutará mientras se pulse el click izquierdo
        }
    }

    public void checkCamInputs() {
        //Comparo la posición anterior con la actual para averiguar a donde se ha movido la cámara
        Point2 mousePosition = getMousePosition(); //Posición actual del ratón
        if (mousePosition == null || mousePreviousPosition == null) { //Si el ratón se sale de la pantalla
            mousePosition = getMousePosition();
            mousePreviousPosition = mousePosition;
            return;
        }
        if (mousePosition != mousePreviousPosition) {
            float rotSpeed = (float)gm.cam.getRotSpeed();
            float dify= mousePosition.y - mousePreviousPosition.y;
            float difx= mousePosition.x - mousePreviousPosition.x;
            if (dify > 0) {
                //Camara up
                //*dify/3
                gm.cam.rotarCamara(0,rotSpeed);
            }
            else if (dify < 0) {
                //Camara down
                gm.cam.rotarCamara(0,-1*rotSpeed);
            }

            if (difx > 0) {
                //Camara right
                gm.cam.rotarCamara(-1*rotSpeed,0);
            }
            else if (difx < 0) {
                //Camara left
                gm.cam.rotarCamara(rotSpeed,0);
            }
        }
        this.mousePreviousPosition = getMousePosition();
    }

    public Point2 getMousePosition() {
        //Por como funciona la librería devuelve la posición del ratón en arrays
        double[] xpos = new double[1], ypos = new double[1];
        long window = GameManager.getInstance().renderer.getWindow();
        GLFW.glfwGetCursorPos(window, xpos, ypos);
        return fixCoords(new Point2((float) xpos[0], (float) ypos[0]));
    }
}
