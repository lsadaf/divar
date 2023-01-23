package DataTypes;

import Exceptions.EmailInvalidException;
import Exceptions.LoginFailedException;
import Exceptions.PasswordInvalidException;
import Exceptions.UsernameInvalidException;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.*;

public class User {
    private String username;
    private String password;
    private String email;
    private URL picture;
    private String name;
    private String lastName;
    private long phoneNumber;

    //setters and getters
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPicture(String picture) throws MalformedURLException {
        this.picture = new URL(picture);
    }

    public URL getPicture() {
        return picture;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public User(String username, String password, String email) throws Exception {
        Pattern p1 = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher m1 = p1.matcher(email);
        Pattern p2 = Pattern.compile("^[a-zA-Z0-9._]{4,}$");
        Matcher m2 = p2.matcher(username);
        Pattern p3 = Pattern.compile("");
        Matcher m3 = p3.matcher(password);
        boolean passCheck = false;
        int checkA=0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i)=='a'){
                checkA++;
            }
        }
        if (checkA>=2 && m3.find()){
            passCheck = true;
        }

           if (!m1.find()) {
               throw new EmailInvalidException("Email Invalid!");
           }
           if (!m2.find()) {
               throw new UsernameInvalidException("Username Invalid!");
           }
           if (!passCheck) {
               throw new PasswordInvalidException("Password Invalid!");
           }
        this.username = username;
        this.password = password;
        this.email = email;
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
