package theorems;

import core.Iff;
import core.Imply;
import core.Or;

import static core.Introduction.left;
import static core.Introduction.right;
import static core.Or.*;

public class DisjunctionIsCommutative<A, B> extends Iff<Or<A, B>, Or<B, A>> {
    public DisjunctionIsCommutative(Imply<Or<A, B>, Or<B, A>> leftToRight, Imply<Or<B, A>, Or<A, B>> rightToLeft) {
        super(leftToRight, rightToLeft);
    }

    public static <A, B> DisjunctionIsCommutative<A, B> prove() {
        return new DisjunctionIsCommutative<>(new Imply<Or<A, B>, Or<B, A>>() {
            @Override
            public Or<B, A> imply(Or<A, B> param) {
                return param.apply(new Imply<A, Or<B, A>>() {
                    @Override
                    public Or<B, A> imply(A a) {
                        return right(a);
                    }
                }, new Imply<B, Or<B, A>>() {
                    @Override
                    public Or<B, A> imply(B b) {
                        return left(b);
                    }
                });
            }
        }, new Imply<Or<B, A>, Or<A, B>>() {
            @Override
            public Or<A, B> imply(Or<B, A> param) {
                return param.apply(new Imply<B, Or<A, B>>() {
                    @Override
                    public Or<A, B> imply(B b) {
                        return right(b);
                    }
                }, new Imply<A, Or<A, B>>() {
                    @Override
                    public Or<A, B> imply(A a) {
                        return left(a);
                    }
                });
            }
        });
    }
}
