package objects;

import java.util.ArrayList;

import math.components.*;
import static math.util.UtilVector.createVector;

public class Cube extends Object {
    private float edgeLength; // Lado del cubo

    public Cube(float x, float y, float z, float edgeLength) {
        super(x, y, z); // Llama al constructor de la clase base (Object)
        this.edgeLength = edgeLength;
    }

//  --Vértices--
//  Cuanto más positivo sea el vertice en "y" antes irá alfabéticamente
//  Cuando más negativo sea el vértice en "x" y "z" antes irán alfabeticamente
//  (prioridades: y>z>x) (mirar vertices.png)

    // Vértices del cubo
    public Point getA() {
        return new Point(x - (edgeLength / 2), y + (edgeLength / 2), z - (edgeLength / 2));
    }

    public Point getB() {
        return new Point(x + (edgeLength / 2), y + (edgeLength / 2), z - (edgeLength / 2));
    }

    public Point getC() {
        return new Point(x - (edgeLength / 2), y + (edgeLength / 2), z + (edgeLength / 2));
    }

    public Point getD() {
        return new Point(x + (edgeLength / 2), y + (edgeLength / 2), z + (edgeLength / 2));
    }

    public Point getE() {
        return new Point(x - (edgeLength / 2), y - (edgeLength / 2), z - (edgeLength / 2));
    }

    public Point getF() {
        return new Point(x + (edgeLength / 2), y - (edgeLength / 2), z - (edgeLength / 2));
    }

    public Point getG() {
        return new Point(x - (edgeLength / 2), y - (edgeLength / 2), z + (edgeLength / 2));
    }

    public Point getH() {
        return new Point(x + (edgeLength / 2), y - (edgeLength / 2), z + (edgeLength / 2));
    }

    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(getA());points.add(getB());points.add(getC());points.add(getD());
        points.add(getE());points.add(getF());points.add(getG());points.add(getH());
        return points;
    }

    // Aristas
    public Vector getAB() {
        return createVector(getA(), getB());
    }

    public Vector getAC() {
        return createVector(getA(), getC());
    }

    public Vector getAD() {
        return createVector(getA(), getD());
    }

    public Vector getBD() {
        return createVector(getB(), getD());
    }

    public Vector getBF() {
        return createVector(getB(), getF());
    }

    public Vector getCD() {
        return createVector(getC(), getD());
    }

    public Vector getCG() {
        return createVector(getC(), getG());
    }

    public Vector getGE() {
        return createVector(getG(), getE());
    }

    public Vector getGH() {
        return createVector(getG(), getH());
    }

    public Vector getEF() {
        return createVector(getE(), getF());
    }

    public Vector getFH() {
        return createVector(getF(), getH());
    }

    // Caras del cubo
    public Plane getTop() {
        return new Plane(getA(), getB(), getC());
    }

    public Plane getBottom() {
        return new Plane(getE(), getF(), getG());
    }

    public Plane getLeft() {
        return new Plane(getC(), getA(), getE());
    }

    public Plane getRight() {
        return new Plane(getB(), getD(), getF());
    }

    public Plane getFront() {
        return new Plane(getC(), getD(), getG());
    }

    public Plane getBack() {
        return new Plane(getA(), getB(), getE());
    }
}
