package ru.cherkasov.w1d4;

public class Starter {
    public static void main(String[] args) {
        FileGeneration fileGeneration = new FileGeneration();
        String[] text = GenerationHelper.genCollectionWords();
        fileGeneration.getFiles("test", 20, 2, text, 100);
    }
}
