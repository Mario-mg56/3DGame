package math.util;

import managers.GameManager;
import math.components.Point2;

public class Utilities {
    public static Point2 fixCoords(Point2 p) {
        //Cambiar el sistema de coordenadas de arriba a la izquierda a el centro de la pantalla
        GameManager gm = GameManager.getInstance();
        if ((Math.abs(p.x) > ((float) gm.width /2)) || (Math.abs(p.y) > ((float) gm.height /2))) {
            return null; //Si el punto se sale de la pantalla
        }
        return new Point2(p.x + ((float) gm.width /2), ((-1)*p.y)+((float) gm.height /2));
    }
}
