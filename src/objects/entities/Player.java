package objects.entities;

import managers.GameManager;
import math.components.Vector;

public class Player extends Entity {
    public Vector movementVector;
    public Player() {
        super();
        this.movementVector = new Vector(0, 0, 0); //Se define en los listeners del InputManagers
    }
}
