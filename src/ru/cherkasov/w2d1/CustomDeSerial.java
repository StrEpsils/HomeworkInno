package ru.cherkasov.w2d1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Диссерилизатор файла
 */
public class CustomDeSerial {

    private String path;

    /**
     * Конструктор
     *
     * @param path путь до файла
     */
    public CustomDeSerial(String path) {
        this.path = path;
    }

    /**
     * Парсим XML файл
     *
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public Document parseDomFile() throws ParserConfigurationException, IOException, SAXException {
        Document doc = null;
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        doc = builder.parse(new File("./test/EntityObject.xml"));

        Element root = doc.getDocumentElement();

        return doc;
    }


    /**
     * Диссерилизуем в обьект
     *
     * @param obj обьект
     * @return готовый обьект
     */
    public Object writeObject(Object obj) throws IOException, SAXException, ParserConfigurationException {
        Field[] fields = obj.getClass().getDeclaredFields();

        this.parseDomFile().getElementsByTagName("name");

        return obj;
    }
}
