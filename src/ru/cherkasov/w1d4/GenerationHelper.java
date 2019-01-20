package ru.cherkasov.w1d4;

import java.util.Random;

/**
 * Ultilit class
 * for generated words, offers and paragraphs;
 *
 * @author SCherkasov
 */
public class GenerationHelper {

    /**
     * Генерируем слово
     *
     * @return возвращаем слово длиной 15 символов
     */
    static String genWord() {
        StringBuilder stringBuilder = new StringBuilder(15); //ограничиваем длину слова длину слова до 15
        int lengthWord = new Random().nextInt(14) + 1; //определим длину слова
        for (int i = 0; i < lengthWord; i++) {
            char temp = (char) ('a' + new Random().nextInt('z' - 'a'));
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }

    /**
     * Строим предложение
     *
     * @return предложение не свыше 15 слов.
     */
    static String genOffer(String[] collectionWord, int p) {
        StringBuilder stringBuilder = new StringBuilder();
        int lengthOffer = new Random().nextInt(14) + 1; //получаем случайную длину предложения
        int wordCounter = 0;
        String[] wordsDelimiter = new String[]{" ", ", "};
        String[] masPunct = {". ", "! ", "? "};
        for (String word : collectionWord) {
            if (Math.random() < 1.0 / p) {
                stringBuilder.append(word);
                wordCounter++;
            } else continue;
            if (wordCounter == lengthOffer) {
                stringBuilder.append(masPunct[new Random().nextInt(masPunct.length)]);
                break;
            } else {
                stringBuilder.append(wordsDelimiter[new Random().nextInt(2)]);
            }
        }
        //Возвращаем предложенее с поднятым первым символом
        if (stringBuilder.length() > 2) {
            return stringBuilder.substring(0, 1).toUpperCase() + stringBuilder.substring(1).toLowerCase();
        }
        return stringBuilder.substring(0, 1).toUpperCase();
    }

    /**
     * Генерация параграфа
     *
     * @return возвращаем сгенерированный параграф
     */
    public String genParagraph(String[] wordCollection, int p) {
        StringBuilder stringBuilder = new StringBuilder();
        int lengthParagraph = new Random().nextInt(14) + 1; //генерируем случайным образом длину параграфа.
        for (int i = 0; i < lengthParagraph; i++) {
            stringBuilder.append(genOffer(wordCollection, p));
            if (i == lengthParagraph - 1) stringBuilder.append("\n\r");
        }
        return stringBuilder.toString();
    }

    /**
     * Генерируем словарь слов
     *
     * @return массив из 1000 слов
     */
    static String[] genCollectionWords() {
        String[] randWords = new String[1000];
        for (int i = 0; i < 1000; i++) {
            randWords[i] = genWord();
        }
        return randWords;
    }

}
