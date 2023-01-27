
package DataTypes;

import Exceptions.*;
import Server.DataBase;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.*;

public class User {
    private String username="";
    private String password="";
    private String email="";
    private URL picture;
    private String name="";
    private String lastName="";
    private String phoneNumber="";
    private ArrayList<String> favoriteAds = new ArrayList<>();

    //setters and getters
    public void setUsername(String username) throws UsernameInvalidException, UserAlreadyExists {
        Pattern p2 = Pattern.compile("^[a-zA-Z0-9._]{4,}$");
        Matcher m2 = p2.matcher(username);
        if (m2.find()) {
            if (DataBase.getUsers().contains(this)){
                DataBase.removeUser(this);
                this.username = username;
                DataBase.addUser(this);
            }else this.username = username;
        } else throw new UsernameInvalidException("Username Invalid!");
    }

    public String getUsername() {
        return username;
    }

    public void setFavoriteAds(ArrayList<String> favoriteAds) {
        this.favoriteAds = favoriteAds;
    }

    public ArrayList<String> getFavoriteAds() {
        return favoriteAds;
    }

    public void setPassword(String password) throws PasswordInvalidException, UserAlreadyExists {
        Pattern p3 = Pattern.compile("(?=[a-z0-9]+$)^(?=.*[a-z])(?=.*[0-9])(?=.{8,}).*$",Pattern.CASE_INSENSITIVE);
        Matcher m3 = p3.matcher(password);
        int checkA=0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i)=='a'){
                checkA++;
            }
        }
        if (checkA>=2 && m3.find()){
            if (DataBase.getUsers().contains(this)){
                DataBase.removeUser(this);
                this.password = password;
                DataBase.addUser(this);
            }else this.password = password;

        }else throw new PasswordInvalidException("Password Invalid!");
    }

    public String getPassword() {
        return password;
    }

    public void setPicture(String picture) throws PictureInvalidException, MalformedURLException, UserAlreadyExists {
        Pattern p =Pattern.compile("^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$");
        Matcher m =p.matcher(picture);
        if (m.find()){
            if (DataBase.getUsers().contains(this)) {
                DataBase.removeUser(this);
            }
            this.picture = new URL(picture);
            DataBase.addUser(this);
        }else throw new PictureInvalidException("Picture Invallid!");
    }

    public URL getPicture() {
        return picture;
    }

    public void setEmail(String email) throws EmailInvalidException, UserAlreadyExists {
        Pattern p1 = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher m1 = p1.matcher(email);
        if (m1.find()){
            if (DataBase.getUsers().contains(this)){
                DataBase.removeUser(this);
                this.email = email;
                DataBase.addUser(this);
            }else this.email = email;
        }
        else throw new EmailInvalidException("Email Invalid!");
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) throws UserAlreadyExists {
        if (DataBase.getUsers().contains(this)) {
            DataBase.removeUser(this);
        }
        this.name = name;
        DataBase.addUser(this);
    }

    public String getName() {
        return name;
    }

    public void setLastName(String lastName) throws UserAlreadyExists {
        if (DataBase.getUsers().contains(this)) {
            DataBase.removeUser(this);
        }
        this.lastName = lastName;
        DataBase.addUser(this);
    }

    public String getLastName() {
        return lastName;
    }

    public void setPhoneNumber(String  phoneNumber) throws PhoneNumberInvalidException, UserAlreadyExists {
        Pattern p1 = Pattern.compile("^09[0-9]{9}$");
        Matcher m1 = p1.matcher(phoneNumber);
        if (m1.find()){
            if (DataBase.getUsers().contains(this)) {
                DataBase.removeUser(this);
            }
            this.phoneNumber = phoneNumber;
            DataBase.addUser(this);
        }else throw new PhoneNumberInvalidException("Phone number invalid!");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User(String username, String password, String email) throws Exception {
        setUsername(username);
        setPassword(password);
        setEmail(email);
    }
    public static User login(String username,String password) throws LoginFailedException {
        for (int i = 0; i < DataBase.getUsers().size(); i++) {
            if (DataBase.getUsers().get(i).getUsername().equals(username) && DataBase.getUsers().get(i).getPassword().equals(password) ){
                return DataBase.getUsers().get(i);
            }
        }
        throw new LoginFailedException("Login failed!");
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static User fromJson(String json) {
        return new Gson().fromJson(json, User.class);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) || email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
