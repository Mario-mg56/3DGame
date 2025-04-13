package math.components;

public class Point2 {
    public float x, y;
    String name;
    public Point2(float x, float y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Point2(float x, float y) {this(x, y, null);}

    public Point2 add(Point2 point) {
        return new Point2(this.x + point.x, this.y + point.y, point.name);
    }

    public Point2 subtract(Point2 point) {
        return new Point2(this.x - point.x, this.y - point.y, point.name);
    }

    @Override public String toString() {return name + "x:" + x + " y:" + y;}
}
