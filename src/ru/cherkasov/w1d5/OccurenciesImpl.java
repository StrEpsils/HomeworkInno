package ru.cherkasov.w1d5;

import ru.cherkasov.w1d5.Resources.ParserResource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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
        Arrays.stream(sources)
                .map(resource -> new ParserResource(resource, res, words))
                .forEach(ParserResource::run);
    }
}
