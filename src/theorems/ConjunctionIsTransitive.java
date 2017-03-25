package theorems;

import core.And;
import core.Iff;
import core.Imply;

public class ConjunctionIsTransitive<A, B, C> extends Iff<And<A, And<B, C>>, And<And<A, B>, C>> {
    public ConjunctionIsTransitive(Imply<And<A, And<B, C>>, And<And<A, B>, C>> leftToRight, Imply<And<And<A, B>, C>, And<A, And<B, C>>> rightToLeft) {
        super(leftToRight, rightToLeft);
    }

    public static <A, B, C> ConjunctionIsTransitive<A, B, C> proof() {
        return new ConjunctionIsTransitive<>(new Imply<And<A, And<B, C>>, And<And<A, B>, C>>() {
            @Override
            public And<And<A, B>, C> imply(And<A, And<B, C>> param) {
                return new And(new And(param.getA(), param.getB().getA()), param.getB());
            }
        }, new Imply<And<And<A, B>, C>, And<A, And<B, C>>>() {
            @Override
            public And<A, And<B, C>> imply(And<And<A, B>, C> param) {
                return new And(param.getA().getA(), new And(param.getA().getB(), param.getB()));
            }
        });
    }
}
