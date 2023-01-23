import Exceptions.EmailInvalidException;
import Exceptions.PasswordInvalidException;
import Exceptions.UsernameInvalidException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.*;
public class User {
    private String username;
    private String password;
    private String email;
    private URL picture;

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

    public User(String username, String password, String email){
        setUsername(username);
        setPassword(password);
    }
    public static void SignUp(String username,String password,String email) throws Exception {
        Pattern p1 = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher m1 = p1.matcher(email);
        Pattern p2 = Pattern.compile("^[a-zA-Z0-9_.]{5,}$");
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
        if (m1.find() && m2.find() && passCheck){ //file
            new User(username,password,email);
        }
        if (!m2.find()){
            throw new UsernameInvalidException("Username Invalid!");
        }
        if (!passCheck){
            throw new PasswordInvalidException("Password Invalid!");
        }
        if (!m1.find()){
            throw new EmailInvalidException("Email Invalid!");
        }
    }
    public static void Login(String username,String password){
        //if ()
    }
    public void Profile(){
        JUI.clearScreen();
        JUI.changeBackgroundColor(JUI.Colors.WHITE);
        JUI.changeColor(JUI.Colors.BOLD_BLACK);
        System.out.println("Profile: ");
        System.out.println("Profile picture: " + picture);
        JUI.changeColor(JUI.Colors.BLACK);
        System.out.println("Name: ");
        System.out.println("Last name: ");
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);

    }
}
