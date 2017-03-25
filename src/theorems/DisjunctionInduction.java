package theorems;

import core.And;
import core.Imply;
import core.Or;

public class DisjunctionInduction<A, B> implements Imply<A, And<Or<A, B>, Or<B, A>>> {
    @Override
    public And<Or<A, B>, Or<B, A>> imply(A param) {
        return null;
    }
}
