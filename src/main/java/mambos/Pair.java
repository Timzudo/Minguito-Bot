package mambos;

public interface Pair<F, S> {

    F getFirst();

    S getSecond();

    void setFirst(F first);

    void setSecond(S second);

}
