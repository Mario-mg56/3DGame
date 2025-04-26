package math.components;

import java.util.ArrayList;

public class Triangule {
    public Point p1;
    public Point p2;
    public Point p3;
    public Triangule(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    public Vector getNormal(){
        Plane plane = new Plane(p1, p2, p3);
        return plane.vn;
    }
    public ArrayList<Point> getPoints(){
        ArrayList<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        return points;
    }
}
