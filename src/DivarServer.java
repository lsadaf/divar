import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class DivarServer implements Runnable {
    private final int port;

    public DivarServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            Socket socket;
            while (true) {
                socket = server.accept();
                new Thread(new DivarRunnable(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DivarRunnable implements Runnable{
    private Socket socket;

    public DivarRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()))){
            String message;
            String response = "";
            message = in.readUTF();
            System.out.println("i got " + message);
            if (message.startsWith("LOGIN=")){
                if (message.contains(",")){
                    String[] st = message.split("=")[1].split(",");
                    String username = st[0];
                    String password = st[1];
                    User.login(username,password);
                }
            }else if (message.startsWith("SIGNUP=")) {
                if (message.contains(",")) {
                    String[] st = message.split("=")[1].split(",");
                    String username = st[0];
                    String password = st[1];
                    String email = st[2];
                    new User(username, password, email);
                    User.login(username,password);
                }
            }else if (message.startsWith("PROFILE")){
                //todo
            }
            out.writeUTF(response);
            out.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}