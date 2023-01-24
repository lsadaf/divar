package Server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    static DataBase data;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()))){
            //request and response here
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*public static void setData (DataBase newData){
        data = newData;
    }*/
}
