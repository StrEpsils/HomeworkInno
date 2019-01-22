package ru.cherkasov.w2d1;

public class Starter {

    public static void main(String[] args) {
        EntityObject entityObject = new EntityObject("Eger", 22, "Engin");
//        System.out.println(entityObject.getClass().getSimpleName());
        CustomSerial cs = new CustomSerial();
        cs.serialObj(entityObject, "test");
    }
}
