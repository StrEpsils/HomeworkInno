package ru.cherkasov.w1d3;

import java.util.*;

/**
 * Класс Коробки;
 *
 * @author SCherkasov
 */
public class ObjectBox {

    protected UUID uuid = UUID.randomUUID();

    private Collection<Object> storage;

    private Collection<Object> getStorage() {
        return storage;
    }

    /**
     * Конструктор без параметров, создает пустой экземпляр <tt>HashSet</tt>
     */
    public ObjectBox() {
        this.storage = new HashSet<>();
    }

    /**
     * Конструктор с входным масивом,
     * который копируется в новый экземпляр HashSet
     *
     * @param array входящий массив данных <tt>Object</tt>
     */
    public ObjectBox(Object[] array) {
        this.storage = new HashSet<>();
        Collections.addAll(this.storage, array);
    }

    /**
     * Добавляем элемент в коллекцию <tt>Object</tt>
     *
     * @param element элемент который добавляем
     * @return boolean
     */
    boolean addObject(Object element) {
        return this.getStorage().add(element);
    }

    /**
     * Удаляем элемент из коллекции
     *
     * @param element элемент который удаляем
     * @return boolean
     */
    boolean deleteObject(Object element) {
        return this.getStorage().remove(element);
    }

    /**
     * Вывод в строчном виде всех элементов коллекции
     *
     * @return
     */
    String dump() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object element : storage) {
            stringBuilder.append(element).append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox objectBox = (ObjectBox) o;
        return Objects.equals(uuid, objectBox.uuid) &&
                Objects.equals(storage, objectBox.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, storage);
    }

    @Override
    public String toString() {
        return "ObjectBox{" +
                "uuid=" + uuid +
                ", ResultStorage=" + storage +
                '}';
    }
}
