package managers;

import java.util.ArrayList;

import math.components.*;
import objects.Object;
import objects.entities.*;
import graphics.*;

public class GameManager {
    private static GameManager instance;
    public InputManager inputManager;
    public Cam cam;
    public Screen screen;
    public Player player;
    public ArrayList<Point2> info2d;
    public ArrayList<Point> info3d;
    public ArrayList<Object> objs;
    private GameManager() {
        this.inputManager = new InputManager();
        this.cam = new Cam();
        this.screen = new Screen();
        this.player = new Player();
        this.info2d = new ArrayList<>();
        this.info3d = new ArrayList<>();
        this.objs = new ArrayList<>();
    }
    public static GameManager getInstance() {
        if (instance == null) {instance = new GameManager();}
        return instance;
    }
    public void update(){

    }

}
