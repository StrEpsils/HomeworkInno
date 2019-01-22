package ru.cherkasov.w2d1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CustomSerialImpl implements CustomSerial {

    private static final Set<String> WRAPPER_TYPES = getWrapperTypes();

    private static Set<String> getWrapperTypes() {
        Set<String> ret = new HashSet<>(Arrays.asList("Boolean", "Character", "Byte", "Short", "Integer", "Long",
                "Float", "Double", "String"));
        return ret;
    }

    /**
     * Сверка ссылочных типов
     *
     * @param type именование ссылочного типа данных
     * @return имеется  / не имеется
     */
    private static boolean isWrapperType(String type) {
        return WRAPPER_TYPES.contains(type);
    }

    /**
     * Сериализация в XML
     *
     * @param obj  сериализуемый обьект
     * @param path путь для сохранения файла
     */
    public void serialObj(Object obj, String path) {
        if (obj == null) return;
        StringBuilder bf = new StringBuilder();
        bf.append("<").append(obj.getClass().getSimpleName()).append(">");
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().isPrimitive() || isWrapperType(field.getType().getSimpleName())) {
                field.setAccessible(true);
                try {
                    bf.append("<")
                            .append(field.getName()).append(" ").append("type=\"")
                            .append(field.getType().getSimpleName()).append("\">")
                            .append(field.get(obj)).append("</")
                            .append(field.getName()).append(">");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        bf.append("</").append(obj.getClass().getSimpleName()).append(">");

        try (OutputStream outStream = new FileOutputStream("./" + path + "/" + obj.getClass().getSimpleName() + ".xml")) {
            outStream.write(bf.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
