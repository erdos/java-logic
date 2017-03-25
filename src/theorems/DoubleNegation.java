
package theorems;

import core.Bottom;
import core.Imply;
import core.Not;

public class DoubleNegation<A> implements Imply<A, Not<Not<A>>> {

    @Override
    public Not<Not<A>> imply(final A a) {
        return new Not<Not<A>>() {
            @Override
            public Bottom imply(Not<A> param) {
                return param.imply(a);
            }
        };
    }

    public static <A> DoubleNegation<A> proof() {
        return new DoubleNegation<>();
    }
}
