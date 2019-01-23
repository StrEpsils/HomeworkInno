package ru.cherkasov.w1d5;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;

/**
 * Парсер файла
 */
public class Parser extends Thread {
    private String path;
    private Set<String> dictionary;
    private BlockingQueue<String> result;

    public Parser(String path, Set<String> dictionary, BlockingQueue<String> result) {
        this.path = path;
        this.dictionary = dictionary;
        this.result = result;
    }

    /**
     * Читалка предложения, проверяем и делим на массив слов
     */
    @Override
    public void run() {
        System.out.println("thread start");
        try(Reader reader = new InputStreamReader(getInputStream(path))) {
            StringBuilder buffer = new StringBuilder();
            int receivedChar;
            while ((receivedChar = reader.read()) != -1) {
                buffer.append((char) receivedChar);
                if (receivedChar == '.' || receivedChar == '!' || receivedChar == '?') {
                    checkSentence(buffer.toString());
                    buffer.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка предложения
     * @param sentence предложение
     */
    private void checkSentence(String sentence) {
        String[] wordsFormSentence = sentence.trim().split("[ ,;:\"]+");
        if (Arrays.stream(wordsFormSentence).anyMatch(s -> dictionary.contains(s))) {
            result.add(sentence);
        }
    }

    /**
     * Получаем правильный поток
     * @param path путь до файла
     * @return открываем нужный поток
     * @throws IOException
     */
    private InputStream getInputStream(String path) throws IOException {
        if (Pattern.matches("^(http(s)?|ftp)://.*", path)) {
            return new URL(path).openStream();
        } else {
            return new BufferedInputStream(new FileInputStream(path));
        }
    }
}
