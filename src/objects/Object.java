package objects;

public class Object {
    protected float x, y, z;
    public Object(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override public String toString() {return "x: " + x + " y: " + y + " z: " + z;}
}
