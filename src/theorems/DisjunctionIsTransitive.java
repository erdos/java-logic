package theorems;

import core.Iff;
import core.Imply;
import core.Or;

import static core.Introduction.left;
import static core.Introduction.right;

public class DisjunctionIsTransitive<A, B, C> extends Iff<Or<A, Or<B, C>>, Or<Or<A, B>, C>> {
    private DisjunctionIsTransitive(Imply<Or<A, Or<B, C>>, Or<Or<A, B>, C>> leftToRight, Imply<Or<Or<A, B>, C>, Or<A, Or<B, C>>> rightToLeft) {
        super(leftToRight, rightToLeft);
    }

    public static <A, B, C> DisjunctionIsTransitive<A, B, C> proof() {
        return new DisjunctionIsTransitive<>(new LeftToRight(), null);
    }

    public static class LeftToRight<A, B, C> implements Imply<Or<A, Or<B, C>>, Or<Or<A, B>, C>> {
        @Override
        public Or<Or<A, B>, C> imply(Or<A, Or<B, C>> param) {
            return param.apply(
                    new Imply<A, Or<Or<A, B>, C>>() {
                        @Override
                        public Or<Or<A, B>, C> imply(A param) {
                            Or<A, B> a = left(param);
                            return left(a);
                        }
                    }, new Imply<Or<B, C>, Or<Or<A, B>, C>>() {
                        @Override
                        public Or<Or<A, B>, C> imply(Or<B, C> param) {
                            return param.apply(
                                    new Imply<B, Or<Or<A, B>, C>>() {
                                        @Override
                                        public Or<Or<A, B>, C> imply(B b) {
                                            Or<A, B> x = right(b);
                                            return left(x);
                                        }
                                    }, new Imply<C, Or<Or<A, B>, C>>() {
                                        @Override
                                        public Or<Or<A, B>, C> imply(C c) {
                                            return right(c);
                                        }
                                    });
                        }
                    });
        }
    }

    public static class RightToLeft<A, B, C> implements Imply<Or<Or<A, B>, C>, Or<A, Or<B, C>>> {

        @Override
        public Or<A, Or<B, C>> imply(Or<Or<A, B>, C> param) {
            return param.apply(
                    new Imply<Or<A, B>, Or<A, Or<B, C>>>() {
                        @Override
                        public Or<A, Or<B, C>> imply(Or<A, B> param) {
                            return param.apply(new Imply<A, Or<A, Or<B, C>>>() {
                                @Override
                                public Or<A, Or<B, C>> imply(A a) {
                                    return left(a);
                                }
                            }, new Imply<B, Or<A, Or<B, C>>>() {
                                @Override
                                public Or<A, Or<B, C>> imply(B b) {
                                    Or<B, C> x = left(b);
                                    return right(x);
                                }
                            });
                        }
                    }, new Imply<C, Or<A, Or<B, C>>>() {
                        @Override
                        public Or<A, Or<B, C>> imply(C c) {
                            Or<B, C> x = right(c);
                            return right(x);
                        }
                    });
        }
    }
}
