package ru.cherkasov.w2d3;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Запуск сервера и случатель новых клиентов
 */
public class Starter {
    static final int PORT = 8090;
    static final String IP = "localhost";
    static List<Server> listClients = new ArrayList<>();

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Server start");
            //Слушаем новых клиентов и кладем их в список
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Connected = [" + socket + "]");
                Server serverClient = new Server(socket);
                listClients.add(serverClient);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
