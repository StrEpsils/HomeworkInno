package ru.cherkasov.w2d3;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {

    public static final int PORT = 5555;

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("start");
        Thread listiner = new ListinerThread(serverSocket);
        listiner.start();

        Scanner scanner = new Scanner(System.in);
        while (!scanner.nextLine().equals("quit")){
            //loop
        }
        System.out.println("stop server...");
        serverSocket.close();
        listiner.interrupt();
        listiner.join();
        System.out.println("Server done");

    }
}
