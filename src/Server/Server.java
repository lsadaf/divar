package Server;

import Client.ActionHandler;
import DataTypes.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*public class Server {
    static ServerSocket serverSocket;
    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(4000);
        while (!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
             new Thread(new ClientHandler(socket)).start();
            System.out.println("New client connected");
        }

    }
}*/
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Server implements Runnable {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            Socket socket;
            while (true) {
                socket = server.accept();
                new Thread((Runnable) new ActionHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
