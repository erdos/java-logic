package core;

public interface Or<A, B> {
    public <C> C apply(Imply<A, C> left, Imply<B, C> right);
}
