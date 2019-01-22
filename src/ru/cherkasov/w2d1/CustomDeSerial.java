package ru.cherkasov.w2d1;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

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
     * @return список Элементов
     */
    public NodeList parseDomFile() {
        Document doc = null;
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            doc = builder.parse(new File(path));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        Node root = doc.getDocumentElement();

        return root.getChildNodes();
    }


    /**
     * Диссерилизуем в обьект
     *
     * @param obj обьект
     * @return готовый обьект
     */
    public Object writeObject(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        NodeList nodeList = this.parseDomFile();
        for (int i = 0; i < nodeList.getLength(); i++) {
            for (Field field : fields) {
                field.setAccessible(true);
                if(field.getName().contains(nodeList.item(i).getNodeName())) {
                    try {
                        if (nodeList.item(i).getNodeName().equals("age")) {
                            field.setInt(obj, Integer.parseInt(nodeList.item(i).getTextContent()));
                        } else {
                            field.set(obj, nodeList.item(i).getTextContent());
                        }
                    }catch (IllegalAccessException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }
}
