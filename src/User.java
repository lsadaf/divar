import Exceptions.EmailInvalidException;
import Exceptions.PasswordInvalidException;
import Exceptions.UsernameInvalidException;

import java.util.regex.*;
public class User {
    private String username;
    private String password;
    private String email;
    public User(String username,String password,String email) throws Exception {
        Pattern p1 = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher m1 = p1.matcher(email);
        Pattern p2 = Pattern.compile("^[a-zA-Z0-9_.]{5,}$");
        Matcher m2 = p2.matcher(username);
        Pattern p3 = Pattern.compile("");
        Matcher m3 = p3.matcher(password);
        if (m1.find() && m2.find() && m3.find()){
            this.email = email;
            this.username = username;
            this.password = password;
        }
        if (!m2.find()){
            throw new UsernameInvalidException("Username Invalid!");
        }
        if (!m3.find()){
            throw new PasswordInvalidException("Password Invalid!");
        }
        if (!m1.find()){
            throw new EmailInvalidException("Email Invalid!");
        }
    }
}
