package math.components;

public class Point implements OperableCartesian<Point>{
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

    @Override public String toString() {
        return this.asString();
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

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public Point create(double x, double y, double z) {
        return new Point(x,y,z);
    }
}
