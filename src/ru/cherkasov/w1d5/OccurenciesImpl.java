package ru.cherkasov.w1d5;

import ru.cherkasov.w1d5.Resources.ParserResource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OccurenciesImpl implements Occurencies {

    /**
     * Метод нахождения вхождений всех слов из массива в предложении
     * @param sources массив ресурсов
     * @param words массив слов
     * @param res путь к файлу
     * @throws IOException
     */
    @Override
    public void getOccurencies(String[] sources, String[] words, String res) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(res))){
            bufferedWriter.write(""); //clear
        }

        for (String resource : sources) {
            Thread thread = new ParserResource(resource, res, words);
            thread.start();
        }
    }
}
