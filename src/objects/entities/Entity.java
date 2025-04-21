package objects.entities;

import math.components.*;

public class Entity {
    public Point position;
    public Entity() {
        this.position = new Point(0, 0, 0);
    }
    public Entity(Point position) {
        this.position = position;
    }
}
