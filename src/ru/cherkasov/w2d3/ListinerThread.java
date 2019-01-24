package ru.cherkasov.w2d3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ListinerThread extends Thread {

    private final ServerSocket serverSocket;
    private final Map<Socket, String> socketUserMap;

    public ListinerThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.socketUserMap = new ConcurrentHashMap<>();
    }

    @Override
    public void run() {
        while (!isInterrupted()){
            try {
                Socket socket = serverSocket.accept();
                socketUserMap.put(socket, "");
                //todo started thread listiner
                OutputStream os = socket.getOutputStream();

                System.out.println("Подключился " + socket);
                InputStream is = socket.getInputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(is));
                String inputText;
                while ((inputText = bf.readLine()) != null){
                    System.out.println(inputText);
                    os.write((inputText+"\n").getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
