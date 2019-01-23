package ru.cherkasov.w1d5;

import java.io.FileNotFoundException;
import java.util.concurrent.BlockingQueue;
import java.io.PrintWriter;

public class OutFiles extends Thread {
    private String outFile;
    private BlockingQueue<String> outCollection;

    public OutFiles(String outFile, BlockingQueue<String> outCollection) {
        this.outFile = outFile;
        this.outCollection = outCollection;
    }

    @Override
    public void run() {
        int count = 0;
        try (PrintWriter printWriter = new PrintWriter(outFile)) {
            while (!Thread.currentThread().isInterrupted()) {
                printWriter.println(outCollection.take());
                System.out.println(count++);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Thread is Interrputed!");
        }
    }
}