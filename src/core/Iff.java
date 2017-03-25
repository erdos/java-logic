package core;

public class Iff<A, B> extends And<Imply<A, B>, Imply<B, A>> {

    // switch sides
    public Iff<B, A> swap() {
        return new Iff(getB(), getA());
    }

    public Iff(Imply<A, B> leftToRight, Imply<B, A> rightToLeft) {
        super(leftToRight, rightToLeft);
    }
}
