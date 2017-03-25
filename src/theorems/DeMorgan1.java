package theorems;

import core.*;

public final class DeMorgan1<P, Q> extends Iff<Not<Or<P, Q>>, And<Not<P>, Not<Q>>> {
    private DeMorgan1(Imply<Not<Or<P, Q>>, And<Not<P>, Not<Q>>> leftToRight, Imply<And<Not<P>, Not<Q>>, Not<Or<P, Q>>> rightToLeft) {
        super(leftToRight, rightToLeft);
    }

    // this is the proof
    public static <A, B> DeMorgan1<A, B> proof() {
        return new DeMorgan1<>(new LeftSide(), new RightSide());
    }

    static class LeftSide<A, B> implements Imply<Not<Or<A, B>>, And<Not<A>, Not<B>>> {
        @Override
        public And<Not<A>, Not<B>> imply(final Not<Or<A, B>> param1) {

            Not<A> andLeft = Not.fromImplication(new Imply<A, Bottom>() {
                @Override
                public Bottom imply(A param) {
                    Or<A, B> x = Introduction.left(param);
                    return param1.imply(x);
                }
            });

            Not<B> andRight = Not.fromImplication(new Imply<B, Bottom>() {
                @Override
                public Bottom imply(B param) {
                    Or<A, B> x = Introduction.right(param);
                    return param1.imply(x);
                }
            });

            return new And<>(andLeft, andRight);
        }
    }

    static class RightSide<A, B> implements Imply<And<Not<A>, Not<B>>, Not<Or<A, B>>> {
        @Override
        public Not<Or<A, B>> imply(final And<Not<A>, Not<B>> param) {

            return Not.fromImplication(new Imply<Or<A, B>, Bottom>() {
                @Override
                public Bottom imply(final Or<A, B> either) {
                    return either.apply(
                            new Imply<A, Bottom>() {
                                @Override
                                public Bottom imply(A a) {
                                    return param.getA().imply(a);
                                }
                            },
                            new Imply<B, Bottom>() {
                                @Override
                                public Bottom imply(B b) {
                                    return param.getB().imply(b);
                                }
                            });
                }
            });
        }
    }
}
