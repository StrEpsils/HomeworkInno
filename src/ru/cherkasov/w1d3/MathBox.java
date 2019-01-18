package ru.cherkasov.w1d3;

import java.util.*;

public class MathBox<T extends Number> extends ObjectBox {

    private Collection<T> storage;

    /**
     * Конструктор с входящим массивом <tt>Number</tt>
     * @param array входящий массив
     */
    public MathBox(T[] array) {
        this.storage = new TreeSet<>(Arrays.asList(array));
    }

    /**
     * Получаем нашу коллекцию
     * @return
     */
    public Collection<T> getStorage() {
        return storage;
    }

    /**
     * Складываем числа в <tt>Double</tt>
     * @return сумма всех элементов коллекции
     */
    Double summator() {
        double temp = 0d;
        for (T value : this.storage) {
            temp += value.doubleValue();
        }
        return temp;
    }

    /**
     * Делим элементы массива на делитель divider
     * складываем результат в отдельную коллекцию
     * @param divider делитель <tt>Number</tt>
     * @return ArrayList с результатом деления
     */
    Collection<Double> splitter(T divider) {
        List<Double> splitList = new ArrayList<>();
        for (T x : this.storage) {
            try {
                splitList.add(x.doubleValue() / divider.doubleValue());
            }catch (NullPointerException e){
                throw new NullPointerException(e.getMessage());
            }
        }
        return splitList;
    }

    /**
     * Удаление элементов из коллекции
     * @param element удаляемый элемент
     * @return boolean
     */
    boolean deleteElement(T element) {
        return super.deleteObject(element);
    }

    /**
     * Добавляем новый элемент в коллекцию
     * @param element элемент который добавляем
     * @return boolean
     */
    @Override
    boolean addObject(Object element) {
        if(!(element instanceof Number)) throw new IllegalArgumentException("Element is not Number");
        return super.addObject(element);
    }
}
