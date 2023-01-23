import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            if (message.equals("salam")) response = "khodahafez";
            else response = "salam";

            out.writeUTF(response);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}