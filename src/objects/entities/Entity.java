package objects.entities;

import math.components.*;

public class Entity {
    public Point position;
    public double speedRate;
    public Entity() {
        this.position = new Point(0, 0, 0);
        this.speedRate = 100.0;
    }
    public Entity(Point position) {
        this.position = position;
    }
}
