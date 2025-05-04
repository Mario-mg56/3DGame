package managers;

import java.util.ArrayList;

import math.components.*;
import objects.Cube;
import objects.Object;
import objects.entities.*;
import graphics.*;

import static math.util.UtilVector.createVector;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class GameManager {
    private static GameManager instance;
    public InputManager inputManager;
    public Cam cam;
    public Renderer renderer;
    public Player player;
    public ArrayList<Point2> info2d;
    public ArrayList<Point> info3d;
    public ArrayList<Object> objs;
    public int width, height;
    private GameManager() {
        this.width = 800;
        this.height = 600;
        this.info2d = new ArrayList<>();
        this.info3d = new ArrayList<>();
        this.objs = new ArrayList<>();
    }

    public static GameManager getInstance() {
        if (instance == null) {instance = new GameManager();}
        return instance;
    }

    public void play(){
        //Estas clases llaman al gm en sus constructores por lo que no pueden ser creadas en el contructor del gm
        this.player = new Player();
        this.cam = new Cam(400);
        this.renderer = new Renderer(width, height);
        this.inputManager = new InputManager();
        setObjs();
        //glfwWindowShouldClose devuelve true si se cierra la ventana
        while (!glfwWindowShouldClose(renderer.getWindow())) { //Game loop
            cam.update();
            inputManager.update();
            renderer.update();
            System.out.println("vx = " + createVector(cam.puntoDeLaCamara, cam.pivotX).getMod());
            System.out.println("vy = " + createVector(cam.puntoDeLaCamara, cam.pivotY).getMod());
        }
        renderer.clean();
    }

    public void setObjs(){
        info3d.addAll(new Cube(500, 0, 0, 100).getPoints());
    }
}
