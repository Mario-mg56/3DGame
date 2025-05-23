package math.components;

import math.util.UtilVector;

import java.util.ArrayList;

public class Rect {
    public Double x, y, z;
    public Vector vDir;
    public Rect(Point p, Vector vd){ //Recta a partir de un punto y un vector
        //Ec paramétrica: (x = x1 + vDir.x*t), (y = y1 + vDir.y*t), (z = z1 + vDir.z*t)
        this.vDir = vd;
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }
    public Rect(Point p1, Point p2){ //Recta a partir de dos puntos
        this(p1, UtilVector.createVector(p2, p1));
    }

    public Point getPoint(double t){
        //Ec paramétrica: (x = x1 + vDir.x*t), (y = y1 + vDir.y*t), (z = z1 + vDir.z*t)
        double px = this.x + this.vDir.x*t;
        double py = this.y + this.vDir.y*t;
        double pz = this.z + this.vDir.z*t;
        return new Point(px, py, pz);
    }
    public Point getPoint(){return new Point(this.x, this.y, this.z);} //t=0

    public Rect rotate(double ang_x, double ang_y, double ang_z){
        return new Rect(getPoint(), this.vDir.rotar(ang_x, ang_y, ang_z));
    }

    public Double getT(Point punto) {
        Double t1 = null, t2 = null, t3 = null;

        if (this.vDir.x != 0) {
            t1 = Math.round((punto.x - this.x) / this.vDir.x * 1000.0) / 1000.0;
        } else if (this.x != punto.x) {
            return null;
        }

        if (this.vDir.y != 0) {
            t2 = Math.round((punto.y - this.y) / this.vDir.y * 1000.0) / 1000.0;
        } else if (this.y != punto.y) {
            return null;
        }

        if (this.vDir.z != 0) {
            t3 = Math.round((punto.z - this.z) / this.vDir.z * 1000.0) / 1000.0;
        } else if (this.z != punto.z) {
            return null;
        }
        ArrayList<Double> tArray = new ArrayList<>();
        if (t1 != null) tArray.add(t1);
        if (t2 != null) tArray.add(t2);
        if (t3 != null) tArray.add(t3);

        if (!tArray.isEmpty()) {
            boolean todosIguales = true;
            Double ref = tArray.get(0);
            for (Double val : tArray) {
                if (!val.equals(ref)) {
                    todosIguales = false;
                    break;
                }
            }
            if (todosIguales) return ref;
        }

        return null;
    }

//    def getT(self, punto: Point):
//    t1, t2, t3 = None, None, None
//
//        if self.vDir.x != 0:
//    t1 = round((punto.x - self.x) / self.vDir.x, 3)
//    elif self.x != punto.x:
//            return None
//
//        if self.vDir.y != 0:
//    t2 = round((punto.y - self.y) / self.vDir.y,3)
//    elif self.y != punto.y:
//            return None
//
//        if self.vDir.z != 0:
//    t3 = round((punto.z - self.z) / self.vDir.z,3)
//    elif self.z != punto.z:
//            return None
//
//    t = [t1,t2,t3]
//    tArray = []
//            for i in t :
//            if i is not None :
//            tArray.append(i)
//            if len(set(tArray))==1:
//            return tArray[0]
//
//            return None

//    def rotate(self, angX, angY, angZ):
//    newP = Point(self.x, self.y, self.z)
//        newP.rotar(angX, angY, angZ)
//    self.vDir = self.vDir.rotar(angX, angY, angZ)
//    self.x = newP.x
//    self.y = newP.y
//    self.z = newP.z



}
