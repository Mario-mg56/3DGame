package math.util;

import math.components.*;

public class Utilities {
    public static boolean isClose(double a, double b) {
        double relTol = 1e-9;
        double absTol = 0.0;
        return Math.abs(a - b) <= Math.max(relTol * Math.max(Math.abs(a), Math.abs(b)), absTol);
    }
    public static Point interseccion_recta_plano(Rect recta, Plane plano){
        Vector v = recta.vDir;
        double x = recta.x;
        double y = recta.y;
        double z = recta.z;
        double A = plano.A;
        double B = plano.B;
        double C = plano.C;
        double D = plano.D;
        double denominador = (A*v.x+B*v.y+C*v.z);
        if (Math.abs(denominador) < 1e-6){
            return null;
        }
        double t = -(A * x + B * y + C * z + D)/denominador;

        return recta.getPoint(t);
    }

}
