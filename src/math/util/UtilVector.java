package math.util;

import math.components.*;

public class UtilVector {
    public static Vector createVector(Point pf, Point p0) {
        return new Vector(pf.x - p0.x, pf.y - p0.y, pf.z - p0.z);
    }

    public static Double scalarProduct(Vector v1, Vector v2) {
        return (v1.x * v2.x + v1.y * v2.y + v1.z * v2.z);
    }

    public static Double getAngle(Vector v1, Vector v2) {
        //Producto escalar v1*v2 = Mod(v1) * Mod(v2) * cos(angúlo entre los vectores)
        //Ángulo entre vectores = arccos(v1*v2/Mod(v1) * Mod(v2))
        return Math.acos((scalarProduct(v1, v2))/(v1.getMod()*v2.getMod()));
    }
}
