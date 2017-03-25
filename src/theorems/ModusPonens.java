package theorems;

import core.And;
import core.Imply;

public final class ModusPonens<A, B> implements Imply<And<A, Imply<A, B>>, B> {

    @Override
    public B imply(And<A, Imply<A, B>> param) {
        A elem = param.getA();
        Imply<A, B> implikacio = param.getB();
        B kovetkezmeny = implikacio.imply(elem);
        return kovetkezmeny;
    }
}
