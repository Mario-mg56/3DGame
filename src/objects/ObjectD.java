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
    //tocar escalado funciona mal
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

    //esta arreglado pero probablemente de problemas
    public ArrayList<Face> getTexturas(Point p) {
        ArrayList<Face> devolviendo = new ArrayList<>();

        for (int i = 0; i < almacenCaras.size(); i++) {
            if (almacenCaras.get(i).getPoints().indexOf(p) != -1) {
                devolviendo.add(almacenCaras.get(i));
            }
        }
        for(ObjectD obj : children){
            if(obj.getPuntos().indexOf(p)==-1){
                continue;
            }
            devolviendo.addAll(obj.getTexturas(getUnTransformedPoint(p,obj.vectorEscalado,obj.centro)));
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

    public ArrayList<Point> getPuntos() {
        ArrayList<Point> puntos = new ArrayList<>();
        for (Point p : almacenPuntos) {
            //aplicar transformaciones
            puntos.add(getTransformedPoint(p));
        }
        return puntos;
    }

    public void removePointFromParents(Point p,ObjectD hijo,Point translate, Vector scale) {
        if(hijo.parent==null){
            return;
        }
        hijo.parent.almacenPuntos.remove(getUnTransformedPoint(p,scale,translate));
        removePointFromParents(getUnTransformedPoint(p,scale,translate),hijo.parent,centro,vectorEscalado);
    }
    public void addPointToParents(Point p,ObjectD hijo) {
        if(hijo.parent==null){
            return;
        }
        hijo.parent.almacenPuntos.add(p);
        addPointToParents(getTransformedPoint(p),hijo.parent);
    }
    public void onTransformChange(Point translate, Vector scale){
        for(Point p : almacenPuntos){
            removePointFromParents(p,this,translate,scale);
        }
        for(Point p : almacenPuntos){
            addPointToParents(getTransformedPoint(p),this);
        }
    }
    //Manage de objeto
    public void añadirCara(Face face){
        almacenCaras.add(face);
        almacenPuntos.addAll(face.getPoints());
        for(Point p : face.getPoints()){
            addPointToParents(p,this);
        }

    }
    public void añadirObjeto(ObjectD obj){
        children.add(obj);
        almacenPuntos.addAll(obj.getPuntos());
        for(Point p : obj.getPuntos()){
            addPointToParents(p,this);
        }
    }
    public void quitarCara(Face face){
        almacenCaras.remove(face);
        almacenPuntos.removeAll(face.getPoints());
        for(Point p : face.getPoints()){
            removePointFromParents(p,this,centro,vectorEscalado);
        }

    }
    public void quitarObjeto(ObjectD obj){
        children.remove(obj);
        almacenPuntos.removeAll(obj.getPuntos());
        for(Point p : obj.getPuntos()){
            removePointFromParents(p,this,centro,vectorEscalado);
        }
    }



    public Point getUnTransformedPoint(Point p){
        return p.subtract(centro).divide(vectorEscalado);
    }
    public Point getTransformedPoint(Point p){
        return p.multiply(vectorEscalado).add(centro);
    }
    public Point getUnTransformedPoint(Point p, Vector scale, Point translate){
        return p.subtract(translate).divide(scale);
    }
    public Point getTransformedPoint(Point p, Vector scale, Point translate){
        return p.multiply(scale).add(translate);
    }
    //transformaciones geometricas
    public void moveTo(Vector vector){
        Point tempTrans = centro;
        Vector tempEscale = vectorEscalado;
        centro.x= vector.x;
        centro.y= vector.y;
        centro.z= vector.z;
        this.onTransformChange(tempTrans,tempEscale);
    }
    public void move(Vector vector){
        Point tempTrans = centro;
        Vector tempEscale = vectorEscalado;
        centro.x+= vector.x;
        centro.y+= vector.y;
        centro.z+= vector.z;
        this.onTransformChange(tempTrans,tempEscale);
    }
    public void scaleTo(Vector vector){
        Point tempTrans = centro;
        Vector tempEscale = vectorEscalado;
        vectorEscalado= vector;
        this.onTransformChange(tempTrans,tempEscale);
    }
    public void scale(Vector vector){
        Point tempTrans = centro;
        Vector tempEscale = vectorEscalado;
        vectorEscalado= vectorEscalado.add(vector);
        this.onTransformChange(tempTrans,tempEscale);
    }

    public void scaleTo(float x,float y,float z){
        scaleTo(new Vector(x,y,z));
    }
    public void scale(float x,float y,float z){
        scale(new Vector(x,y,z));
    }
    public void moveTo(double x, double y ,double z){
        moveTo(new Vector(x,y,z));
    }
    public void move(double x, double y ,double z){
        move(new Vector(x,y,z));
    }
}
