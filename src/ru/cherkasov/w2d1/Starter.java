package ru.cherkasov.w2d1;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Starter {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
//        EntityObject entityObject = new EntityObject("Eger", 22, "Engin");
////        System.out.println(entityObject.getClass().getSimpleName());
//        CustomSerial cs = new CustomSerial();
//        cs.serialObj(entityObject, "test");

        CustomDeSerial customDeSerial = new CustomDeSerial("123");
        customDeSerial.parseDomFile();
    }
}
