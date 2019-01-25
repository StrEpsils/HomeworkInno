package ru.cherkasov.w1d5.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Парсер
 */
public class ParserResource extends Thread {

    private String res;
    private volatile String path;
    private String[] words;

    /**
     * @param res ресурс
     * @param path путь к файлу
     * @param words массив слов
     */
    public ParserResource(String res, String path, String[] words) {
        this.path = path;
        this.res = res;
        this.words = words;
    }


    /**
     * Запускаем поток на обработку входных данных
     * Разделяем входящий текст на массив предложений,
     * с последующим разделением на слова.
     * Сохраняем в файл.
     */
    public void run() {
        System.out.println(currentThread().getName());
        Sources sources = new SourcesImpl();
        StringBuilder sb = new StringBuilder();
        try(BufferedReader bf = sources.openSourceStream(res)) {
            bf.lines().forEach(line -> sb.append(line).append(" "));
            String[] listSentence = sb.toString().split("[.]");
            for (String s : listSentence) {
                for (String word : words) {
                    boolean found = Arrays.asList(s.replaceAll("/W", " ")
                            .split(" ")).contains(word);
                    if (!found) continue;
                    SaveFile file = new SaveFile(path, s.trim());
                    file.saveFromFile();
                    System.out.println("findWord " + currentThread().getName());
                }
            }
            sb.setLength(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
