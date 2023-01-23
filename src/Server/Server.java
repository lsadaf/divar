package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket serverSocket;
    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(4000);
        DataHolder dataHolder = new DataHolder();
        ClientHandler.setData(dataHolder);
        while (!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
             new Thread(new ClientHandler(socket)).start();
            System.out.println("New client connected");
        }

    }
}
