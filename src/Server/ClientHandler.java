package Server;

import DataTypes.*;
import Communication.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    static DataHolder data;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (true){
            RequestPacket requestPacket = null;
            try {
                requestPacket = (RequestPacket) objectInputStream.readObject();
                if ( requestPacket.getRequestType() == RequestType.GET_ALL_ADS){
                    ResponsePacket responsePacket = new ResponsePacket(data.advertisements);
                    sendResponse(responsePacket);
                }
                if ( requestPacket.getRequestType() == RequestType.NEW_AD){
                    Advertisement advertisement = (Advertisement) requestPacket.getData();
                    advertisement.setCreatorID("CREATOR ID...");
                    advertisement.setAdvertisementID((data.advertisements.size() + 1) + "");
                    data.advertisements.add(advertisement);
                }
            } catch (IOException | ClassNotFoundException ignored) {

            }
        }
    }
    public void sendResponse (ResponsePacket responsePacket){
        try {
            objectOutputStream.writeUnshared(responsePacket);
        } catch (IOException ignored) {
        }
    }

    public static void setData (DataHolder newData){
        data = newData;
    }
}
