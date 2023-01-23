package Server;

import DataTypes.*;
import Communication.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    static DataHolder data;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //get Communication packages from client and handle each command
    }

    public static void setData (DataHolder newData){
        data = newData;
    }
}
