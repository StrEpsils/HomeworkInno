package ru.cherkasov.w1d5;

import java.io.IOException;

public class Starter {

    public static void main(String[] args) throws IOException {
        String[] sources = new String[]{"./test/warpeace.txt", "./test/warpeace.txt", "./test/warpeace.txt"};
        String[] words = new String[]{"Petersburg"};
        String res = "./test/outFile.txt";
        OccurenciesImpl occurencies = new OccurenciesImpl();
        occurencies.getOccurencies(sources, words, res);
    }
}
