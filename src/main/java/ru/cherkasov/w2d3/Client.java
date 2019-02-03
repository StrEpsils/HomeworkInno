package ru.cherkasov.w2d3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Клиент для многопоточного чата
 */
public class Client {
    public static void main(String[] args) {

        try(Socket socket = new Socket(Starter.IP,Starter.PORT);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){

            Listener listener = new Listener(socket);
            listener.start();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Say me your name: ");
            String nickname = scanner.nextLine();
            System.out.println("Welcome! " + nickname);
            String message = "";
            while(true){
                message = scanner.nextLine();
                if(message.equals("quit")){
                    break;
                }
                bufferedWriter.write(nickname + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}