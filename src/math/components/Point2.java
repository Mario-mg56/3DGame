package math.components;

public class Point2 {
    public double x, y;
    String name;
    public Point2(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Point2(double x, double y) {this(x, y, null);}

    public Point2 add(Point2 point) {
        return new Point2(this.x + point.x, this.y + point.y, point.name);
    }

    public Point2 subtract(Point2 point) {
        return new Point2(this.x - point.x, this.y - point.y, point.name);
    }

    @Override public String toString() {return name + "x:" + x + " y:" + y;}
}
