package ru.cherkasov.w2d1;

/**
 * Класс сущности
 */
public class EntityObject {
    private String name;
    private int age;
    private String position;

    public EntityObject(String name, int age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    @Override
    public String toString() {
        return "EntityObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                '}';
    }
}
