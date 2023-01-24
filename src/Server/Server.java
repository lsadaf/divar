package Server;

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
                new Thread(new DivarRunnable(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DivarRunnable implements Runnable{
    private Socket socket;

    public DivarRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()))){
            String message;
            String response = "";
            message = in.readUTF();
            out.writeUTF(response);
            out.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
