package Server;

import DataTypes.Advertisement;
import DataTypes.User;

import java.util.ArrayList;

public class DataHolder {
    ArrayList<User> users;
    ArrayList<Advertisement> advertisements;

    public DataHolder() {
        loadData();
    }

    public void loadData (){
        //load data from files/DB
    }
    public void saveData (){
        //save data to file/DB
    }
}
