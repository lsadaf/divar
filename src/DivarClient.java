import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DivarClient {
    public static void main(String[] args) throws IOException {
        int port = 8357;

        new Thread(new DivarServer(port)).start();

        Socket socket = new Socket("localhost", port);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        dos.writeUTF("rtgrt");
        dos.flush();
        String response = dis.readUTF();
        System.out.println(response);
        dos.close();
        socket.close();
    }
}
