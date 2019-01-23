package ru.cherkasov.w1d5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.*;

public class OccurenciesImpl implements Occurencies {
    private final static int MAX_THREADS = 10;
    private ExecutorService poolThread = Executors.newFixedThreadPool(MAX_THREADS);
    private BlockingQueue<String> outCollection = new LinkedBlockingQueue<>();

    @Override
    public void getOccurencies(String[] sources, String[] words, String res) {
        long l = System.currentTimeMillis();
        System.out.println("Start" + l);
        OutFiles resultFileWriter = new OutFiles(res, outCollection);
        resultFileWriter.start();

        for (String source : sources) {
            poolThread.submit(new Parser(source, new HashSet<>(Arrays.asList(words)), outCollection));
        }
        poolThread.shutdown();
        try {
            poolThread.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resultFileWriter.interrupt();
        System.out.println("Finish " + (System.currentTimeMillis() - l));
    }
}
