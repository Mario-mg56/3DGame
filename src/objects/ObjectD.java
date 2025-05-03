package objects;

import math.components.Triangule;
import math.components.Point;
import math.components.Vector;
import math.components.Face;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectD {

    //importante usa cordenadas locales
    ObjectD parent;
    ArrayList<Point> almacenPuntos;
    ArrayList<Face> almacenCaras;
    ArrayList<ObjectD> children;
    //cambiar por la clase real de las texturas
    //nota de momento se emparejan 1:1 con el almacen de triangulos por lo tanto
    Vector vectorEscalado = new Vector(1,1,1);
    Point centro;
    public ObjectD(ArrayList<Face> almacenCaras,ArrayList<ObjectD> children) {
        this.centro = new Point(0,0,0);
        this.almacenCaras = almacenCaras;
        this.children = children;
        setupParent();
        setupArrayPuntos();
    }
    public ObjectD(ArrayList<Face> almacenCaras,ArrayList<ObjectD> children, Point centro) {
        this.centro = centro;
        this.almacenCaras = almacenCaras;
        this.children = children;
        setupParent();
        setupArrayPuntos();
    }
    private void setupParent(){
        if(children.size()==0){
            return;
        }
        for(ObjectD obj : children){
            obj.parent = this;
        }
    }
    public ArrayList<Face> getTexturas(Point p) {
        ArrayList<Face> devolviendo = new ArrayList<>();

        for (int i = 0; i < almacenCaras.size(); i++) {
            if (almacenCaras.get(i).getPoints().indexOf(p) != -1) {
                devolviendo.add(almacenCaras.get(i));
            }
        }
        for(ObjectD obj : children){
            if(obj.getPuntos().indexOf(p.subtract(obj.centro).divide(obj.vectorEscalado))==-1){
                continue;
            }
            devolviendo.addAll(obj.getTexturas(p.subtract(obj.centro).divide(obj.vectorEscalado)));
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
        for(ObjectD o : children){
            almacenPuntos.addAll(o.getPuntos());
        }

    }
    //al hacer un cambio en un hijo tengo q reportar los cambios al padre
    public ArrayList<Point> getPuntos() {
        ArrayList<Point> puntos = new ArrayList<>();
        for (Point p : almacenPuntos) {
            //aplicar transformaciones
            puntos.add(p.add(centro).multiply(vectorEscalado));
        }
        return puntos;
    }


    public void onTransformChange(Point anterior, Point posterior){

    }
    //Manage de objeto
    public void añadirCara(Face face){
        almacenCaras.add(face);
        almacenPuntos.addAll(face.getPoints());
    }
    public void añadirObjeto(ObjectD obj){
        children.add(obj);
        almacenPuntos.addAll(obj.getPuntos());
    }
    public void quitarCara(){

    }
    public void quitarObjeto(){

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
