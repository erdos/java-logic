package core;

public interface Imply<A, B> {
    B imply(A param);
}