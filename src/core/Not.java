package core;

/**
 * Created by erdos on 3/24/17.
 */
public abstract class Not<A> implements Imply<A, Bottom> {

    public static <A> Not<A> fromImplication(final Imply<A, Bottom> impl) {
        return new Not<A>() {
            @Override
            public Bottom imply(A param) {
                return impl.imply(param);
            }
        };
    }
}
