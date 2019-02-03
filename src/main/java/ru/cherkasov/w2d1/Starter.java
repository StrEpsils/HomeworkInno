package ru.cherkasov.w2d1;

public class Starter {

    public static void main(String[] args) {
        EntityObject entityObject = new EntityObject("Egor", 22, "Developer");
        CustomSerialImpl cs = new CustomSerialImpl();
        cs.serialObj(entityObject, "test");

        CustomDeSerialImpl customDeSerial = new CustomDeSerialImpl("./test/EntityObject.xml");
        EntityObject entityObject1 = (EntityObject) customDeSerial.writeObject(entityObject);
        System.out.println(entityObject1.toString());
    }
}
