import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathServer implements Runnable {
    private final int port;

    public MathServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            Socket socket;
            while (true) {
                socket = server.accept();
                new Thread(new MathRunnable(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MathRunnable implements Runnable{
    private Socket socket;

    public MathRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()))){
            String message;
            int result = -1;
            message = in.readUTF();
            String pattern = "What is (.+) (.+) (.+)\\?";
            Pattern r = Pattern.compile(pattern);

            Matcher matcher = r.matcher(message);

            if (matcher.find()) {
                if (matcher.group(2).equals("add")) result = Integer.parseInt(matcher.group(1)) + Integer.parseInt(matcher.group(3));
                else if (matcher.group(2).equals("sub")) result = Integer.parseInt(matcher.group(1)) - Integer.parseInt(matcher.group(3));
                else if (matcher.group(2).equals("multiply")) result = Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(3));
                else if (matcher.group(2).equals("divideBy")) result = Integer.parseInt(matcher.group(1)) / Integer.parseInt(matcher.group(3));

                out.writeUTF("The result of " + matcher.group(1) + " " + matcher.group(2) + " " + matcher.group(3) + " is: " + result);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MathServerClient {
    public static void main(String[] args) throws IOException {
//        int port = 8356;
//
//        new Thread(new MathServer(port)).start();
//
//        Socket socket = new Socket("localhost", port);
//        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//        DataInputStream dis = new DataInputStream(socket.getInputStream());
//        dos.writeUTF("What is 13 multiply 2?");
//        dos.flush();
//        String response = dis.readUTF();
//        System.out.println(response);
//        dos.close();
//        socket.close();

        System.out.print("The color of ");
        JUI.changeColor(JUI.Colors.BOLD_CYAN);
        System.out.print("this");
        JUI.changeColor(JUI.Colors.DEFAULT);
        System.out.println(" should be different!");

        System.out.print("The type of ");
        JUI.bold();
        System.out.print("this");
        JUI.bold();
        System.out.println(" should be bold!");

        System.out.print("The type of ");
        JUI.italic();
        System.out.print("this");
        JUI.italic();
        System.out.println(" should be italic!");

        System.out.print("The type of ");
        JUI.underline();
        System.out.print("this");
        JUI.underline();
        System.out.println(" should be underlined!");

        System.out.print("The type of ");
        JUI.strikethrough();
        System.out.print("this");
        JUI.strikethrough();
        System.out.println(" should be strikethrough!");

        JUI.changeBackgroundColor(JUI.Colors.RED);
        System.out.println("This line should have different background color!");
        JUI.changeBackgroundColor(JUI.Colors.DEFAULT);
    }
}
