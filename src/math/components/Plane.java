package math.components;

public class Plane {
    public double A, B, C, D;
    Point p;
    Vector vn;
    public Plane(Point p, Vector vn) { //Plano a partir de un punto y un vector normal al plano
        //Ec del plano: Ax + By + Cz + D = 0
        this.p = p;
        this.vn = vn;
        this.A = vn.x;
        this.B = vn.y;
        this.C = vn.z;
        this.D = -(this.A*p.x+this.B*p.y+this.C*p.z);
    }
    public Plane(Point p1, Point p2, Point p3) { //Plano a partir de tres puntos
        this.p = p1;
        //Explicación en DesarrolloPlano.png
        this.A = ((p2.y-p1.y)*(p3.z-p1.z)-(p2.z-p1.z)*(p3.y-p1.y));
        this.B = ((p2.z-p1.z)*(p3.x-p1.x)-(p2.x-p1.x)*(p3.z-p1.z));
        this.C = ((p2.x-p1.x)*(p3.y-p1.y)-(p2.y-p1.y)*(p3.x-p1.x));
        this.D = -(this.A*p1.x+this.B*p1.y+this.C*p1.z);
        this.vn = new Vector(A, B, C); //A, B y C equivalen a las componentes del vector normal al plano
    }

    @Override public String toString() {return A + "*x + " + B + "*y + " + C + "*z +" + D;}

    public Plane rotar(double anguloX, double anguloY, double anguloZ){
        this.p = this.p.rotar(anguloX, anguloY, anguloZ);
        this.vn = this.vn.rotar(anguloX, anguloY, anguloZ);
        this.A = vn.x;
        this.B = vn.y;
        this.C = vn.z;
        this.D = -(this.A*p.x+this.B*p.y+this.C*p.z);
        return this;
    }
}