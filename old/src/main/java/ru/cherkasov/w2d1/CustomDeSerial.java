package ru.cherkasov.w2d1;

import org.w3c.dom.NodeList;

public interface CustomDeSerial {

    /**
     * Метод для парсинга XML
     *
     * @return NodeList с элементами
     */
    NodeList parseDomFile();

    /**
     * Метод для десериализации
     *
     * @param obj обьект
     * @return Десериализованный экземпляр
     */
    Object writeObject(Object obj);

}
