package mambos;

public class PairClass<F, S> implements Pair{
    private F first;
    private S second;

    public PairClass(F first, S second){
        this.first = first;
        this.second = second;
    }

    public F getFirst(){
        return first;
    }

    public S getSecond(){
        return second;
    }

    @Override
    public void setFirst(Object first) {
        this.first = (F) first;
    }

    @Override
    public void setSecond(Object second) {
        this.second = (S) second;
    }

}
