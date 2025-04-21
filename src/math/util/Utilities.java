package math.util;

import managers.GameManager;
import math.components.Point2;
import math.components.*;

public class Utilities {
    public static boolean isClose(double a, double b) {
        double relTol = 1e-9;
        double absTol = 0.0;
        return Math.abs(a - b) <= Math.max(relTol * Math.max(Math.abs(a), Math.abs(b)), absTol);
    }
    
    public static Point2 fixCoords(Point2 p) {
        //Cambiar el sistema de coordenadas de arriba a la izquierda a el centro de la pantalla
        GameManager gm = GameManager.getInstance();
        if ((Math.abs(p.x) > ((float) gm.width /2)) || (Math.abs(p.y) > ((float) gm.height /2))) {
            return null; //Si el punto se sale de la pantalla
        }
        return new Point2(p.x + ((float) gm.width /2), ((-1)*p.y)+((float) gm.height /2));
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
