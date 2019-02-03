package ru.cherkasov.w2d3;

import java.io.*;
import java.net.Socket;

/**
 * Сервер многопоточного чата
 */
class Server extends Thread {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    Server(Socket socket) throws IOException {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }
    public void run(){
        String message;
        try{
            while (true) {
                message = bufferedReader.readLine();
                if(message.equals("quit")){
                    interrupt();
                }
                System.out.println("Message from " + message);
                //Рассылаем всем клиентам соощения
                for (Server i : Starter.listClients) {
                    i.bufferedWriter.write(message + "\n");
                    i.bufferedWriter.flush();
                }
            }
        }catch(Exception e){
            System.out.println("Disconnect = [" + socket + "]");
        }finally {
            Starter.listClients.remove(this);
            try {
                bufferedReader.close();
                bufferedWriter.close();
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}