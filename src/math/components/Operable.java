package math.components;

public interface Operable<T extends Operable<T>> {
    T add(T other);
    void addS(T other);
    T subtract(T other);
    void subtractS(T other);
    T multiply(T other);
    void multiplyS(T other);
    T divide(T other);
    void divideS(T other);
}
