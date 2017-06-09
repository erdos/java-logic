package core;

/**
 * Helper functions to introduce disjunctions where one side is already proven.
 *
 */
public enum Introduction {
    ;

    public static <A, B> Or<A, B> left(final A a) {
        return new Or<A, B>() {
            @Override
            public <C> C apply(Imply<A, C> left, Imply<B, C> right) {
                return left.imply(a);
            }
        };
    }

    public static <A, B> Or<A, B> right(final B b) {
        return new Or<A, B>() {
            @Override
            public <C> C apply(Imply<A, C> left, Imply<B, C> right) {
                return right.imply(b);
            }
        };
    }


}
