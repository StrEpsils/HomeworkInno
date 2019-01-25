package ru.cherkasov.w1d5.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Парсер
 */
public class ParserResource extends Thread {

    private String source; //стрим откуда берем
    private volatile String path; //путь
    private String[] words; //список слов

    public ParserResource(String res, String path, String[] words) {
        this.path = path;
        this.source = res;
        this.words = words;
    }


    public void run() {
        System.out.println(currentThread().getName());
        Sources sources = new SourcesImpl();
        StringBuilder sb = new StringBuilder();
        try(BufferedReader bf = sources.openSourceStream(source)) {
            String line;
            while ((line = bf.readLine()) != null) {
                sb.append(line).append(" ");
            }
            String[] listSentence = sb.toString().split("[.]");
            for (String s : listSentence) {
                for (String word : words) {
                    boolean found = Arrays.asList(s.replaceAll("/W", " ")
                            .split(" ")).contains(word);
                    if (!found) continue;
                    //TODO реализовать запись в файл
                    SaveFile file = new SaveFile(path, s.trim());
                    file.saveFromFile();
                    System.out.println(s.trim());
                }
            }
            sb.setLength(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
