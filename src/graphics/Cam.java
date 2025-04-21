package graphics;

import jdk.jshell.execution.Util;
import managers.GameManager;
import math.components.*;
import math.util.UtilRect;
import math.util.UtilVector;
import math.util.Utilities;

public class Cam {
    double focalDistance, rotSpeed;
    Point puntoDeLaCamara, position;
    GameManager gm;
    private Vector vectorNormal,vector_a_la_camara,vector_X_local,vector_Y_local;
    private Plane plano_camara;
    public Cam(int field_of_view){
        this.focalDistance = field_of_view;
        this.rotSpeed = 2;
        this.gm = GameManager.getInstance();
        this.position = gm.player.position;
        this.puntoDeLaCamara = new Point(position.x+focalDistance, position.y, position.z);
    }
    private void update(){

    }
    public void rotarCamara(float horizontal, float vertical){
        Point puntoCamaraCentrado = this.puntoDeLaCamara.subtract(this.position);
        if (horizontal != 0){
            puntoCamaraCentrado.rotar(0,horizontal,0);
        }
        if (vertical != 0){
            puntoCamaraCentrado.rotateVertically(vertical);
        }
        this.puntoDeLaCamara = puntoCamaraCentrado.add(this.position);
        vector_X_local.normalize();
        actualizar_plano();

    }

    private void actualizar_plano(){
        this.position = gm.player.position;
        vectorNormal = UtilVector.createVector(puntoDeLaCamara,position);
        plano_camara = new Plane(puntoDeLaCamara,vectorNormal);
        vector_a_la_camara = UtilVector.createVector(puntoDeLaCamara,position);
        vector_X_local = UtilVector.producto_vectorial(vector_a_la_camara,UtilVector.getDown());
        vector_Y_local = UtilVector.producto_vectorial(vector_a_la_camara,vector_X_local);
    }

    private void info3Dto2D(){
        for (Point i : gm.info3d){
            Point2 p = watchDiego(i);
            if (p != null){
                gm.info2d.add(p);
            }
        }
    }
    private Point2 watchDiego(Point point){
        Rect ray = new Rect(position,point);
        Point cutPoint = Utilities.interseccion_recta_plano(ray,plano_camara);
        if (cutPoint != null){
            return null;
        }
        if (!(ray.getT(cutPoint) <= 0 && ray.getT(cutPoint) >= ray.getT(point))) {
            return null;
        }
        Vector vectorAlPunto = UtilVector.createVector(cutPoint,puntoDeLaCamara);
        double distancia = vectorAlPunto.getMod();

        double[] resultado = UtilVector.getCuadrante_y_angulo(vectorAlPunto,vector_X_local,vector_Y_local);
        double signx = resultado[0];
        double signy = resultado[1];
        double angulo = resultado[2];
        double x_local = distancia*Math.cos(angulo);
        double y_local = distancia*Math.sin(angulo)*signy;
        return new Point2((float)x_local,(float)y_local);
    }

    public void rotacionCamaraY(double angY){
        Vector vectorXenEje = vector_X_local;
        double ang_nor = UtilVector.getAngle(UtilVector.getLeft(),vectorXenEje);
        vectorXenEje.rotar(0,Math.toDegrees(ang_nor),0);
        if (Utilities.isClose(vectorXenEje.normalize().x,UtilVector.getLeft().x)){
            puntoDeLaCamara.rotar(0,Math.toDegrees(ang_nor),0);
            puntoDeLaCamara.rotar(0,0,Math.toDegrees(angY));
            puntoDeLaCamara.rotar(0,-1*Math.toDegrees(ang_nor),0);
        } else{
            puntoDeLaCamara.rotar(0,-1*Math.toDegrees(ang_nor),0);
            puntoDeLaCamara.rotar(0,0,Math.toDegrees(angY));
            puntoDeLaCamara.rotar(0,Math.toDegrees(ang_nor),0);
        }
        actualizar_plano();
    }
    //falta listen rotation camara añadir comentarios de python y funciones


}
