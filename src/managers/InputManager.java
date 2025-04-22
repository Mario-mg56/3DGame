package managers;

import math.components.Point2;
import static math.util.Utilities.fixCoords;
import org.lwjgl.glfw.GLFW;

public class InputManager {
    Point2 mousePreviousPosition;
    private GameManager gm;
    public InputManager() {
        this.mousePreviousPosition = getMousePosition();
        setUpListeners();
        gm = GameManager.getInstance();

    }

    private void setUpListeners(){
        long window = GameManager.getInstance().renderer.getWindow();
        GLFW.glfwSetKeyCallback(window, (windowHandle, key, scancode, action, mods) -> {
            //Listener teclas presionadas
            if (action == GLFW.GLFW_PRESS){
                switch (key){
                    case GLFW.GLFW_KEY_W:
                        break;
                    case GLFW.GLFW_KEY_A:
                        break;
                    case GLFW.GLFW_KEY_S:
                        break;
                    case GLFW.GLFW_KEY_D:
                        break;
                    case GLFW.GLFW_MOUSE_BUTTON_LEFT:
                        break;
                    case GLFW.GLFW_MOUSE_BUTTON_RIGHT:
                        break;
                    case GLFW.GLFW_KEY_ESCAPE:
                        break;
                }
            }
        });
        //Listener botones del ratón
        GLFW.glfwSetMouseButtonCallback(window, (windowHandle, button, action, mods) -> {
            if (action == GLFW.GLFW_PRESS) {
                if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                    //Click izq
                } else if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                    //Click dcho
                }
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
        checkCamInputs();
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
            if (mousePosition.y > mousePreviousPosition.y) {
                //Camara up
                gm.cam.rotarCamara(0,rotSpeed);
            }
            else if (mousePosition.y < mousePreviousPosition.y) {
                //Camara down
                gm.cam.rotarCamara(0,-1*rotSpeed);
            }
            if (mousePosition.x > mousePreviousPosition.x) {
                //Camara right
                gm.cam.rotarCamara(rotSpeed,0);
            }
            else if (mousePosition.x < mousePreviousPosition.x) {
                //Camara left
                gm.cam.rotarCamara(-1*rotSpeed,0);
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
