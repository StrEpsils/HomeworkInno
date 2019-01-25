package ru.cherkasov.w1d5.Resources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SaveFile {
    private String path;
    private String sentence;

    /**
     * @param path путь к файлу
     * @param sentence предложение
     */
    public SaveFile(String path, String sentence) {
        this.path = path;
        this.sentence = sentence;
    }

    /**
     * Сохранение в файл с дозаписью
     * @throws IOException
     */
    public void saveFromFile() throws IOException {
        try(OutputStream os = new FileOutputStream(path, true)) {
            os.write((sentence + "\n\r").getBytes());
        }
    }

}
