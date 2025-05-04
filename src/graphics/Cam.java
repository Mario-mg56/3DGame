package graphics;

import managers.GameManager;
import math.components.*;
import math.util.UtilVector;
import static math.util.UtilVector.createVector;
import math.util.Utilities;

public class Cam {
    double fov, rotSpeed;
    public Point puntoDeLaCamara, position;
    GameManager gm;
    public Vector vectorNormal, vectorALaCam, vector_X_local, vector_Y_local;
    private Plane plano_camara;
    public Rect axisX, axisY; //Ejes en 2d de la screen
    public Point pivotX, pivotY; //Puntos para poder rotar correctamente los ejes al rotar la cam
    public Cam(int field_of_view){
        this.fov = field_of_view;
        this.rotSpeed = 1;
        this.gm = GameManager.getInstance();
        this.position = gm.player.position;
        this.puntoDeLaCamara = new Point(position.x+fov, position.y, position.z);
        this.vectorALaCam= createVector(puntoDeLaCamara, position);
        setUpAxis();
        actualizar_plano();
    }

    public void update(){
        actualizar_plano();
        info3Dto2D();
    }

    private void setUpAxis(){
        //Para poder crear correctamente los ejes el ángulo vertical (El ángulo del vectorALaCam
        //con el eje y) tiene que ser 90
        double vertAng = Math.toDegrees(UtilVector.getAngle(new Vector(0, 1, 0), vectorALaCam.normalize()));
        if (vertAng != 90) this.puntoDeLaCamara = new Point(position.x+fov, position.y, position.z);
        //Como el ángulo vertical es 90, el vector entre el p cam y el p cam + j es el eje y en 2d
        this.pivotY = new Point(puntoDeLaCamara.x, puntoDeLaCamara.y+1, puntoDeLaCamara.z);
        Vector vectorY = createVector(pivotY, puntoDeLaCamara);
        this.axisY = new Rect(puntoDeLaCamara, vectorY);
        //El eje x 2d es perpendicular tanto al eje y 2d, como al vector a la cam por lo que
        //el p vectorial entre el vectorALaCam y el eje y 2d es el eje x 2d
        Vector vectorX = UtilVector.producto_vectorial(vectorY, vectorALaCam.normalize());
        this.axisX = new Rect(puntoDeLaCamara, vectorX);
        this.pivotX = puntoDeLaCamara.add(vectorX);
    }

    public void rotarCamara(float horizontal, float vertical){
        Point puntoCamaraCentrado = this.puntoDeLaCamara.subtract(position);
        Point pivotXCentrado = this.pivotX.subtract(position);
        Point pivotYCentrado = this.pivotY.subtract(position);
        if (horizontal != 0){
            puntoCamaraCentrado.rotar(0,horizontal,0);
            pivotXCentrado.rotar(0,horizontal,0);
            pivotYCentrado.rotar(0,horizontal,0);
        }
        if (vertical != 0){
            puntoCamaraCentrado.rotateVertically(vertical);
            pivotXCentrado.rotateVertically(vertical);
            pivotYCentrado.rotateVertically(vertical);
        }
        this.puntoDeLaCamara = puntoCamaraCentrado.add(position);
        this.pivotX = pivotXCentrado.add(position);
        this.pivotY = pivotYCentrado.add(position);
        this.axisX = new Rect(puntoDeLaCamara, pivotX);
        this.axisY = new Rect(puntoDeLaCamara, pivotY);
        vector_X_local.normalize();
        this.actualizar_plano();
    }

    private void actualizar_plano(){
        this.position = gm.player.position;
        this.vectorNormal = createVector(puntoDeLaCamara, position);
        this.plano_camara = new Plane(puntoDeLaCamara, vectorNormal);
        this.vectorALaCam = createVector(puntoDeLaCamara, position);
        this.vector_X_local = UtilVector.producto_vectorial(vectorALaCam, UtilVector.getDown());
        this.vector_Y_local = UtilVector.producto_vectorial(vectorALaCam, vector_X_local);
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

        Vector vectorAlPunto = createVector(cutPoint, puntoDeLaCamara);
        double distancia = vectorAlPunto.getMod();

        double[] resultado = UtilVector.getCuadrante_y_angulo(vectorAlPunto,vector_X_local,vector_Y_local);
        double signx = resultado[0];
        double signy = resultado[1];
        double angulo = resultado[2];
        double x_local = distancia*Math.cos(angulo);
        double y_local = distancia*Math.sin(angulo)*signy;
        return new Point2((float)x_local, (float) y_local);
    }

    private Point2 watch2(Point target){
        Rect ray = new Rect(position, target);
        Point cutPoint = Utilities.interseccion_recta_plano(ray, plano_camara);
        if (cutPoint == null) return null;

        //Si la distancia entre la cam y cutPoint es mayor que la distancia entre la cam y el objetivo
        //Quiere decir que el objetivo no se encuentra en nuestro campo de visión
        Vector cam_cp = createVector(cutPoint, position);
        Vector cam_tg = createVector(target, position);
        if (cam_cp.getMod() > cam_tg.getMod()) return null;

        //Si la distancia entre la cam y el target es menor que la distancia entre el punto de la cam
        //y el target quiere decir que el objetivo no se encuentra en nuestro campo de visión
        Vector pcam_tg = createVector(target, puntoDeLaCamara);
        if (cam_tg.getMod() < pcam_tg.getMod()) return null;



        return null;
    }

    public double getRotSpeed() {
        return rotSpeed;
    }

    public void addPosition(Vector vector){
        puntoDeLaCamara.add(vector);
        pivotX.add(vector);
        pivotY.add(vector);
    }

    public void substractPosition(Vector vector){
        puntoDeLaCamara.subtract(vector);
        pivotX.subtract(vector);
        pivotY.subtract(vector);
    }

    //falta añadir comentarios de python y funciones
}
