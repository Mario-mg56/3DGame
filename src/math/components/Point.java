package math.components;

public class Point {
    public double x, y, z;
    String name;
    public Point(double x, double y, double z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Point(double x, double y, double z) {
        this(x, y, z, null);
    }

    public Point add(Point punto) {
        return new Point(this.x + punto.x, this.y + punto.y, this.z + punto.z);
    }

    public Point subtract(Point punto) {
        return new Point(this.x - punto.x, this.y - punto.y, this.z - punto.z);
    }

    @Override public String toString() {
        return "x:" + x + " y:" + y + " z:" + z;
    }

    @Override public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Point) {
            return ((Point) obj).x == this.x && ((Point) obj).y == this.y && ((Point) obj).z == this.z;
        }
        return false;
    }

    public Matrix toMatrix() {
        return new Matrix(1, 3, new double[]{x, y, z});
    }

    public Vector toVector() {
        return new Vector(x, y, z);
    }

    public Point rotar(double anguloX, double anguloY, double anguloZ) {
        Point p = this.toMatrix().rotar(anguloX, anguloY, anguloZ).toPoint();
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
        return p;
    }

    public Point rotateVertically(double anguloY) { //Mirar RotaciónVertical.png
        double alpha = Math.asin(this.y / Math.sqrt(this.x * this.x + this.y * this.y));
        double beta = Math.toRadians(anguloY);
        double gamma = alpha + beta;

        double sena = Math.sin(alpha), cosa = Math.cos(alpha);
        double seng = Math.sin(gamma), cosg = Math.cos(gamma);

        this.x = this.x * cosg / cosa;
        this.z = this.z * cosg / cosa;

        if (alpha == 0) {this.y = Math.sin(beta);} //Indeterminación 0/0, teorema de L'Hopital
        else {this.y = this.y * seng / sena;}

        return this;
    }
}
