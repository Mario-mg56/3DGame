package math.util;

import math.components.*;

public class UtilVector {

    /**
     * @param pf punto final del vector creado
     * @param p0 punto origen del vector creado
     * @return esta funcion devuelve un Vector creado por los dos puntos
     *
     * **/
    public static Vector createVector(Point pf, Point p0) {
        return new Vector(pf.x - p0.x, pf.y - p0.y, pf.z - p0.z);
    }

    /**
     * @param v1 primer termino del producto escalar
     * @param v2 segundo termino del producto escalar
     * @return devuelve el producto escalar entre v1 y v2 pull origin main
     * **/
    public static Double scalarProduct(Vector v1, Vector v2) {
        return (v1.x * v2.x + v1.y * v2.y + v1.z * v2.z);
    }

    public static Double getAngle(Vector v1, Vector v2) {
        //Producto escalar v1*v2 = Mod(v1) * Mod(v2) * cos(angúlo entre los vectores)
        //Ángulo entre vectores = arccos(v1*v2/Mod(v1) * Mod(v2))
        return Math.acos((scalarProduct(v1, v2))/(v1.getMod()*v2.getMod()));
    }
    public static Vector producto_vectorial(Vector v, Vector u) {
        return new Vector(
                (v.y * u.z) - (v.z * u.y),
                -1*((v.x * u.z) - (v.z * u.x)),
                (v.x * u.y) - (v.y * u.x)
        );
    }
    public static double[] getCuadrante_y_angulo(Vector v, Vector vx, Vector vy) {
        double anguloX = UtilVector.getAngle(v, vx);
        double anguloY = UtilVector.getAngle(v, vy);

        double angX = Math.toDegrees(anguloX);
        double angY = Math.toDegrees(anguloY);

        if (0 <= angX && angX <= 90) {
            if (0 <= angY && angY <= 90) {
                return new double[]{1, 1, anguloX};
            } else if (91 <= angY && angY <= 180) {
                return new double[]{1, -1, anguloX};
            }
        } else if (91 <= angX && angX <= 180) {
            if (0 <= angY && angY <= 90) {
                return new double[]{-1, 1, anguloX};
            } else if (91 <= angY && angY <= 180) {
                return new double[]{-1, -1, anguloX};
            }
        }

        return new double[]{0, 0, 0.0}; // valor por defecto si no entra en ninguna condición
    }

    public static Vector getUp() {
            return new Vector(0,1,0);

    }
    public static Vector getDown() {
            return new Vector(0,-1,0);

    }
    public static Vector getRight() {
            return new Vector(1,0,0);

    }
    public static Vector getLeft() {
            return new Vector(-1,0,0);

    }
}
