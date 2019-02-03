package ru.cherkasov.w1d4;

import java.io.*;

/**
 * Генератор файлика
 */
public class FileGeneration {

    /**
     * Генерируем файл со случайным содержимым
     *
     * @param path        путь до файла
     * @param n           количество генерируемых файлов
     * @param size        кол-во абзацев
     * @param words       массив слов
     * @param probability вероятность
     */
    void getFiles(String path, int n, int size, String[] words, int probability) {

        for (int i = 0; i < n; i++) {
            try (OutputStream outputStream = new FileOutputStream("./" + path + "/" + "extract_" + i + ".txt", false)) {
                for (int j = 0; j < size; j++) {
                    GenerationHelper generationHelper = new GenerationHelper();
                    byte[] buffer = generationHelper.genParagraph(words, probability).getBytes();
                    outputStream.write(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
