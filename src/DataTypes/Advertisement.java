package DataTypes;

import CLI.Colors;
import com.google.gson.Gson;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Advertisement implements Serializable {
    private String creatorID;
    private String advertisementID;
    private String name;
    private String description;
    private String[] pics;
    private double price;
    private String location;
    private String city;
    private String phone;
    private LocalDateTime lastUpdate;
    private boolean isPrioritized;

    public Advertisement(String name, String description, double price, String city, String location, String phone, LocalDateTime lastUpdate,String[] pics,  boolean isPrioritized) {
        this.name = name;
        this.pics = pics;
        this.price = price;
        this.location = location;
        this.phone = phone;
        this.lastUpdate = lastUpdate;
        this.isPrioritized = isPrioritized;
        this.description = description;
        if ( city != null){
            this.city = city.toUpperCase();
        }
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static User fromJson(String json) {
        return new Gson().fromJson(json, User.class);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String[] getPics() {
        return pics;
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
        return "ID = " + advertisementID + "\n" + Colors.colorPrint(Colors.WHITE_BOLD_BRIGHT, name) + "\n" + price + "\n" + city;
    }
    public void printDetails (){
        System.out.println("ID = " + advertisementID + "\n" + Colors.colorPrint(Colors.WHITE_BOLD_BRIGHT, name) + "\nPRICE = " + price);
        System.out.println(description);
        System.out.println("ADDRESS = " + city + ", " + location);
        System.out.println("PHONE NUMBER = " + phone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = lastUpdate.format(formatter);
        System.out.println("UPDATED " + formattedDateTime);
    }
}
