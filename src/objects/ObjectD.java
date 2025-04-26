package objects;

import math.components.Triangule;
import math.components.Point;
import math.components.Vector;
import math.components.Face;

import java.awt.*;
import java.util.ArrayList;

public class ObjectD {
    //importante usa cordenadas locales

    ArrayList<Point> almacenPuntos;
    ArrayList<Face> almacenCaras;
    //cambiar por la clase real de las texturas
    //nota de momento se emparejan 1:1 con el almacen de triangulos por lo tanto
    Vector vectorEscalado = new Vector(1,1,1);
    Point centro;
    public ObjectD(ArrayList<Face> almacenCaras) {
        this.centro = new Point(0,0,0);
        this.almacenCaras = almacenCaras;
        setupArrayPuntos();
    }
    public ObjectD(ArrayList<Face> almacenCaras, Point centro) {
        this.centro = centro;
        this.almacenCaras = almacenCaras;
        setupArrayPuntos();
    }

    public ArrayList<Face> getTexturas(Point p) {
        ArrayList<Face> devolviendo = new ArrayList<>();

        for (int i = 0; i < almacenCaras.size(); i++) {
            if(devolviendo.size() == 3){
                break;
            }
            if (almacenCaras.get(i).getPoints().indexOf(p) != -1) {
                devolviendo.add(almacenCaras.get(i));
            }
        }
        return devolviendo;
    }

    public void setupArrayPuntos(){
        almacenPuntos = new ArrayList<>();
        for (Face t : almacenCaras) {
            for (int i = 0; i < 3; i++) {
                boolean encontrado = false;
                for(Point p : almacenPuntos) {
                    if(p == t.getPoints().get(i)){
                        encontrado = true;
                    }
                }
                if(!encontrado){
                    almacenPuntos.add(t.getPoints().get(i));
                }
            }

        }
    }

    public ArrayList<Point> getPuntos() {
        ArrayList<Point> puntos = new ArrayList<>();
        for (Point p : almacenPuntos) {
            //aplicar transformaciones
            puntos.add(p.add(centro).multiply(vectorEscalado));
        }
        return puntos;
    }

    //transformaciones geometricas
    public void moveTo(double x, double y ,double z){
        centro.x= x;
        centro.y= y;
        centro.z= z;
    }
    public void move(double x, double y ,double z){
        centro.x+= x;
        centro.y+= y;
        centro.z+= z;
    }
    public void moveTo(Vector vector){
        centro.x= vector.x;
        centro.y= vector.y;
        centro.z= vector.z;
    }
    public void move(Vector vector){
        centro.x+= vector.x;
        centro.y+= vector.y;
        centro.z+= vector.z;
    }
    public void scaleTo(float x,float y,float z){
        vectorEscalado= new Vector(x,y,z);
    }
    public void scale(float x,float y,float z){
        vectorEscalado= vectorEscalado.add(new Vector(x,y,z));
    }
    public void scaleTo(Vector vector){
        vectorEscalado= vector;
    }
    public void scale(Vector vector){
        vectorEscalado= vectorEscalado.add(vector);
    }

}
