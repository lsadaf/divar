package Server;

import DataTypes.*;
import Communication.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ClientHandler implements Runnable {
    static DataBase data;
    private final Socket socket;
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
                    ResponsePacket responsePacket = new ResponsePacket(DataBase.getAds());
                    sendResponse(responsePacket);
                }
                else if ( requestPacket.getRequestType() == RequestType.NEW_AD){
                    Advertisement advertisement = (Advertisement) requestPacket.getData();
                    advertisement.setAdvertisementID((DataBase.getAds().size() + 1) + "");
                    DataBase.addAdvertisement(advertisement);
                }
                else if ( requestPacket.getRequestType() == RequestType.ADD_AD_TO_FAVORITES){
                    String[] requestSplit = ((String)requestPacket.getData()).split("; "); //requestSplit[0] = adId , requestSplit[1] userId
                    ArrayList<User> users = DataBase.getUsers();
                    for ( User user : users){
                        if ( user.getUsername().equals(requestSplit[1])){
                            user.getFavoriteAds().add(requestSplit[0]);
                        }
                        DataBase.saveUsers(users);
                    }
                }
                else if ( requestPacket.getRequestType() == RequestType.GET_FAVORITE_ADS){
                    ArrayList<Advertisement> favoritesAdds = new ArrayList<>();
                    String userId = ((String)requestPacket.getData());
                    ArrayList<Advertisement> allAds = DataBase.getAds();
                    for ( String id : getUser(DataBase.getUsers(), userId).getFavoriteAds()){
                        favoritesAdds.add(getAd(allAds, id));
                    }
                    ResponsePacket responsePacket = new ResponsePacket(favoritesAdds);
                }
                else if ( requestPacket.getRequestType() == RequestType.REMOVE_AD_FROM_FAVORITES){
                    String[] requestSplit = ((String)requestPacket.getData()).split("; "); //requestSplit[0] = adId , requestSplit[1] userId
                    ArrayList<User> users = DataBase.getUsers();
                    for ( User user : users){
                        if ( user.getUsername().equals(requestSplit[1])){
                            user.getFavoriteAds().remove(requestSplit[0]);
                            break;
                        }
                    }
                    DataBase.saveUsers(users);

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

    public static void setData (DataBase newData){
        data = newData;
    }

    public static Advertisement getAd (ArrayList<Advertisement> advertisements,String id){
        for (Advertisement advertisement : advertisements){
            if ( advertisement.getAdvertisementID().equals(id)){
                return advertisement;
            }
        }
        return null;
    }
    public static User getUser (ArrayList<User> users, String username){
        for (User user : users){
            if ( user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

}
