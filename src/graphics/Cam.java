package graphics;

import managers.GameManager;
import math.components.*;
import math.util.UtilVector;
import math.util.Utilities;

public class Cam {
    double fov, rotSpeed;
    public Point puntoDeLaCamara, position;
    GameManager gm;
    public Vector vectorNormal, vector_a_la_camara, vector_X_local, vector_Y_local;
    private Plane plano_camara;
    public Cam(int field_of_view){
        this.fov = field_of_view;
        this.rotSpeed = 1;
        this.gm = GameManager.getInstance();
        this.position = gm.player.position;
        this.puntoDeLaCamara = new Point(position.x+fov, position.y, position.z);
        actualizar_plano();
    }

    public void update(){
        actualizar_plano();
        this.position = gm.player.position;
        //this.puntoDeLaCamara = new Point(position.x+fov, position.y, position.z);
        info3Dto2D();
    }

    public void rotarCamara(float horizontal, float vertical){
        //this.position = gm.player.position;
        //this.puntoDeLaCamara = new Point(position.x+fov, position.y, position.z);

        Point puntoCamaraCentrado = this.puntoDeLaCamara.subtract(position);
        if (horizontal != 0){
            puntoCamaraCentrado.rotar(0,horizontal,0);

        }
        if (vertical != 0){
            puntoCamaraCentrado.rotateVertically(vertical);
        }
        this.puntoDeLaCamara = puntoCamaraCentrado.add(position);
        vector_X_local.normalize();
        this.actualizar_plano();
    }

    private void actualizar_plano(){
        this.position = gm.player.position;
        vectorNormal = UtilVector.createVector(puntoDeLaCamara, position);
        plano_camara = new Plane(puntoDeLaCamara, vectorNormal);
        vector_a_la_camara = UtilVector.createVector(puntoDeLaCamara, position);
        vector_X_local = UtilVector.producto_vectorial(vector_a_la_camara, UtilVector.getDown());
        vector_Y_local = UtilVector.producto_vectorial(vector_a_la_camara, vector_X_local);

    }

    private void info3Dto2D(){
        gm.info2d.clear();
        for (Point i : gm.info3d){
            Point2 p = watch(i);
            if (p != null){
                gm.info2d.add(p);
            }
        }
    }

    private Point2 watch(Point point){
        Rect ray = new Rect(position, point);
        Point cutPoint = Utilities.interseccion_recta_plano(ray, plano_camara);
        if (cutPoint == null){
            return null;
        }
        double cutT = ray.getT(cutPoint);
        if (!(cutT > 0 && cutT<ray.getT(point))) {
            return null;
        }

        Vector vectorAlPunto = UtilVector.createVector(cutPoint, puntoDeLaCamara);
        double distancia = vectorAlPunto.getMod();

        double[] resultado = UtilVector.getCuadrante_y_angulo(vectorAlPunto,vector_X_local,vector_Y_local);
        double signx = resultado[0];
        double signy = resultado[1];
        double angulo = resultado[2];
        double x_local = distancia*Math.cos(angulo);
        double y_local = distancia*Math.sin(angulo)*signy;
        return new Point2((float)x_local, (float) y_local);
    }
    public double getRotSpeed() {
        return rotSpeed;
    }
    //falta añadir comentarios de python y funciones
}
