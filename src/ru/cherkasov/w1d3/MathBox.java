package ru.cherkasov.w1d3;

import java.util.*;

public class MathBox<T extends Number> extends ObjectBox {

    private Set<T> store;

    public MathBox(T[] numbers) {
        this.store = new TreeSet<>(Arrays.asList(numbers));
    }

    private Set<T> getStore() {
        return store;
    }

    protected Double summator(){
        double sum = 0d;
        for (T x : this.store){
            sum += x.doubleValue();
        }
        return sum;
    }

    protected List<Double> splitter(T arg){
        List<Double> list = new ArrayList<>();
        for (T x : this.store) {
            list.add(x.doubleValue()/arg.doubleValue());
        }
        return list;
    }

    boolean delElem(T elem){
        return this.deleteObject(elem);
    }
}
