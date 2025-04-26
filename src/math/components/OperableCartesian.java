package math.components;

public interface OperableCartesian<T extends  OperableCartesian<T>> {
    T create(double x, double y, double z);
    double getX();
    double getY();
    double getZ();
    void setX(double x);
    void setY(double y);
    void setZ(double z);

    default boolean equals(OperableCartesian<?> other){
        if(other==null) return false;
        return getX() == other.getX() && getY() == other.getY() && getZ() == other.getZ();
    }
    default T add(OperableCartesian<?> other) {
        return create(
                this.getX()+other.getX(),
                this.getY()+other.getY(),
                this.getZ()+other.getZ()
        );
    }
    default void addS(OperableCartesian<?> other) {
        this.setX(this.getX() + other.getX());
        this.setY(this.getY() + other.getY());
        this.setZ(this.getZ() + other.getZ());
        }

    default T subtract(OperableCartesian<?> other) {
        return create(
                this.getX()-other.getX(),
                this.getY()-other.getY(),
                this.getZ()-other.getZ()
        );
    }
    default void subtractS(OperableCartesian<?> other) {
        this.setX(this.getX() - other.getX());
        this.setY(this.getY() - other.getY());
        this.setZ(this.getZ() - other.getZ());
    }
    default T multiply(OperableCartesian<?> other) {
        return create(
                this.getX()*other.getX(),
                this.getY()*other.getY(),
                this.getZ()*other.getZ()
        );
    }
    default void multiplyS(OperableCartesian<?> other) {
        this.setX(this.getX() * other.getX());
        this.setY(this.getY() * other.getY());
        this.setZ(this.getZ() * other.getZ());
    }
    default T multiply(Double other) {
        return create(
                this.getX()*other,
                this.getY()*other,
                this.getZ()*other
        );
    }
    default void multiplyS(Double other) {
        this.setX(this.getX() * other);
        this.setY(this.getY() * other);
        this.setZ(this.getZ() * other);
    }
    default T divide(OperableCartesian<?> other) {
        return create(
                this.getX()/other.getX(),
                this.getY()/other.getY(),
                this.getZ()/other.getZ()
        );
    }
    default void divideS(OperableCartesian<?> other) {
        this.setX(this.getX() / other.getX());
        this.setY(this.getY() / other.getY());
        this.setZ(this.getZ() / other.getZ());
    }
    default Matrix toMatrix() {
        return new Matrix(1, 3, new double[]{getX(), getY(), getZ()});
    }
    default String asString(){
        return "x: "+ getX()+" y: "+getY()+" z "+getZ();
    }
}
