package core;

public class And<A, B> {
    private final A a;
    private final B b;


    public And(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }
}
