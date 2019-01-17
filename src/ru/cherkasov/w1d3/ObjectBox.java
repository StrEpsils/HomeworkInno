package ru.cherkasov.w1d3;

import java.util.*;

public class ObjectBox<T> {

    private Collection<T> storage;

    public ObjectBox() {
        this.storage = new ArrayList<>();
    }

    private Collection<T> getStorage() {
        return storage;
    }

    boolean addObject(T element){
        return this.storage.add(element);
    }

    boolean deleteObject(T element){
        return this.getStorage().remove(element);
    }

    String dump(){
        StringBuilder stringBuilder = new StringBuilder();
        for (T t : this.storage) {
            stringBuilder.append(t);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox<?> objectBox = (ObjectBox<?>) o;
        return Objects.equals(storage, objectBox.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage);
    }
}
