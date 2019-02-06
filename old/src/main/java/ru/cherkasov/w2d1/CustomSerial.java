package ru.cherkasov.w2d1;

public interface CustomSerial {

    /**
     * Читаем обьект и серилизуем в XML
     *
     * @param obj  входной экземпляр обьекта
     * @param path путь сохранения
     */
    void serialObj(Object obj, String path);

}
