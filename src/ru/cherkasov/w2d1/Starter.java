package ru.cherkasov.w2d1;

public class Starter {

    public static void main(String[] args) {
        EntityObject entityObject = new EntityObject("Egor", 22, "Developer");
        CustomSerial cs = new CustomSerial();
        cs.serialObj(entityObject, "test");

        CustomDeSerial customDeSerial = new CustomDeSerial("./test/EntityObject.xml");
        EntityObject entityObject1 = (EntityObject) customDeSerial.writeObject(entityObject);
        System.out.println(entityObject1.toString());
    }
}
