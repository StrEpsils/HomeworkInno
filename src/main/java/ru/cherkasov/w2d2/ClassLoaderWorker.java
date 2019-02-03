package ru.cherkasov.w2d2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Кастомный класслоадер
 */
public class ClassLoaderWorker extends ClassLoader {
    /**
     * Метод поиска нашего байт-кода
     *
     * @param name полное название класса
     * @return хот релоад класса
     * @throws ClassNotFoundException класс не найден
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("Find class task started...");
        if (("ru.cherkasov.w2d2.WorkerImpl").equals(name)) ;
        {
            try {
                byte[] bytesClass = Files.readAllBytes(Paths.get("./src/ru/cherkasov/w2d2/WorkerImpl.class"));
                return defineClass(name, bytesClass, 0, bytesClass.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
