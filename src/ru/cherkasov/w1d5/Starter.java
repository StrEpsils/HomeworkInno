package ru.cherkasov.w1d5;

import java.io.IOException;

public class Starter {


    public static void main(String[] args) throws IOException {
        String[] words = {"like", "place"};
        String[] resources = {
                "http://www.gutenberg.org/cache/epub/17168/pg17168.txt",
                "http://www.gutenberg.org/cache/epub/17168/pg17168.txt",
                "http://www.gutenberg.org/cache/epub/17168/pg17168.txt",
                "http://www.gutenberg.org/cache/epub/17168/pg17168.txt",
                "http://www.gutenberg.org/cache/epub/17168/pg17168.txt",
                "http://www.gutenberg.org/cache/epub/17168/pg17168.txt",
                "http://www.gutenberg.org/cache/epub/17168/pg17168.txt",
                "http://www.gutenberg.org/cache/epub/17168/pg17168.txt",
                "http://www.gutenberg.org/cache/epub/17168/pg17168.txt",
                "http://www.gutenberg.org/cache/epub/17168/pg17168.txt",
                "http://www.gutenberg.org/cache/epub/17168/pg17168.txt",
                "./test/extract_0.txt"};
        Occurencies occurencies = new OccurenciesImpl();
        occurencies.getOccurencies(resources, words, "./test/test.txt");
    }

}
