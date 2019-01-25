package ru.cherkasov.w1d5.Resources;

import java.io.*;
import java.net.URL;

public class SourcesImpl implements Sources {

    /**
     * Матод для определения ссылок
     * @return boolean
     */
    private boolean definitionSources(String res) {
        try{
            new URL(res);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Открываем нужный поток
     * @return поток
     * @throws IOException
     */
    @Override
    public BufferedReader openSourceStream(String res) {
        InputStream is = null;
        try {
            if (definitionSources(res)) {
                is = new URL(res).openStream();
                System.out.println("Internet resources open stream!");
            } else {
                is = new FileInputStream(res);
                System.out.println("Local file open stream!");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return new BufferedReader(new InputStreamReader(is));
    }
}
