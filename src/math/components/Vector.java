package math.components;

public class Vector implements OperableCartesian<Vector> {
    public double x, y, z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override public String toString() {
        return this.asString();
    }



    public double getMod() { // Devuelve el módulo del vector
        return Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public Vector normalize() {
        double mod = getMod();
        return new Vector(this.x / mod, this.y / mod, this.z / mod);
    }

    public Vector rotar(double anguloX, double anguloY, double anguloZ) {
        Vector v = this.toMatrix().rotar(anguloX, anguloY, anguloZ).toVector();
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        return v;
    }

    public Vector rotateVertically(double anguloY) { //Mirar RotaciónVertical.png
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
    public Vector create(double x, double y, double z) {
        return new Vector(x,y,z);
    }
}

