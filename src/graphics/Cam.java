package graphics;

import managers.GameManager;
import math.components.*;

public class Cam {
    double focalDistance, rotSpeed;
    Point puntoDeLaCam, center;
    public Cam(){
        this.focalDistance = 400;
        this.rotSpeed = 2;
        this.center = GameManager.getInstance().player.position;
        this.puntoDeLaCam = new Point(center.x+focalDistance, center.y, center.z);
    }
}
