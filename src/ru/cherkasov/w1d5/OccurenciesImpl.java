package ru.cherkasov.w1d5;

import ru.cherkasov.w1d5.Resources.ParserResource;

public class OccurenciesImpl implements Occurencies {
    @Override
    public void getOccurencies(String[] sources, String[] words, String res) {
        for (String resource : sources) {
            ParserResource parserResource = new ParserResource(resource, res, words);
            parserResource.run();
        }
    }
}
