package DataTypes;

import CLI.Colors;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Advertisement {
    private String creatorID;
    private String advertisementID;
    private String name;
    private String description;
    private ArrayList<String> pics;
    private double price;
    private String location;
    private String phone;
    private LocalDateTime lastUpdate;
    private boolean isPrioritized;

    public Advertisement(String creatorID, String advertisementID, String name, String description, double price, String location, String phone, LocalDateTime lastUpdate, boolean isPrioritized) {
        this.creatorID = creatorID;
        this.advertisementID = advertisementID;
        this.name = name;
        this.pics = new ArrayList<>();
        this.price = price;
        this.location = location;
        this.phone = phone;
        this.lastUpdate = lastUpdate;
        this.isPrioritized = isPrioritized;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
    }

    public String getAdvertisementID() {
        return advertisementID;
    }

    public void setAdvertisementID(String advertisementID) {
        this.advertisementID = advertisementID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPics() {
        return pics;
    }

    public void addPic(String pic) {
        this.pics.add(pic);
    }
    public void removePic (int index){
        if ( index >= 0 && index < this.pics.size()){
            this.pics.remove(index);
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isPrioritized() {
        return isPrioritized;
    }

    public void setPrioritized(boolean prioritized) {
        isPrioritized = prioritized;
    }

    public String toString (){
        return "ID = " + advertisementID + "\n" + Colors.colorPrint(Colors.WHITE_BOLD, name) + "\n" + price;
    }
}
