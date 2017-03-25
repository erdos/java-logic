package theorems;

import core.*;

public class DeMorgan2<P, Q> extends Iff<Not<And<P, Q>>, Or<Not<P>, Not<Q>>> {
    public DeMorgan2(Imply<Not<And<P, Q>>, Or<Not<P>, Not<Q>>> leftToRight, Imply<Or<Not<P>, Not<Q>>, Not<And<P, Q>>> rightToLeft) {
        super(leftToRight, rightToLeft);
    }

    public static <P, Q> DeMorgan2<P, Q> proof() {
        return new DeMorgan2<>(new LeftSide(), new RightSide());
    }



    public static class RightSide<P, Q> implements Imply<Or<Not<P>, Not<Q>>, Not<And<P, Q>>> {
        @Override
        public Not<And<P, Q>> imply(final Or<Not<P>, Not<Q>> disjunction) {
            return Not.fromImplication(new Imply<And<P, Q>, Bottom>() {
                @Override
                public Bottom imply(final And<P, Q> param) {
                    return disjunction.apply(new Imply<Not<P>, Bottom>() {
                        @Override
                        public Bottom imply(Not<P> notP) {
                            return notP.imply(param.getA());
                        }
                    }, new Imply<Not<Q>, Bottom>() {
                        @Override
                        public Bottom imply(Not<Q> notQ) {
                            return notQ.imply(param.getB());
                        }
                    });
                }
            });
        }
    }

    public static class LeftSide<P, Q> implements Imply<Not<And<P,Q>>,Or<Not<P>,Not<Q>>> {
        @Override
        public Or<Not<P>, Not<Q>> imply(final Not<And<P, Q>> param) {
            // itt hiba van.
            return new Or<Not<P>, Not<Q>>() {
                @Override
                public <C> C apply(final Imply<Not<P>, C> left, final Imply<Not<Q>, C> right) {

                    return left.imply(new Not<P>() {
                        @Override
                        public Bottom imply(final P p) {
                            return null;
                        }
                    });
                }
            };
        }
    }
}
