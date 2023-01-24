package Server;

import DataTypes.Advertisement;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Server {
    static ServerSocket serverSocket;
    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(4000);
        DataHolder dataHolder = new DataHolder();
        //dataHolder.advertisements = new ArrayList<>(); for test
       /* ArrayList<Advertisement> advertisements = new ArrayList<>(); test
        advertisements.add(new Advertisement( "Book", "This is test 1 for advertisements", 18.99, "Tehran", "abc, def, ghi", "number", LocalDateTime.of(2000, 10, 23, 20, 45),null, false));
        advertisements.add(new Advertisement("Toy", "This is test 2 for advertisements", 25.60, "Shiraz", "abc, def, ghi", "number", LocalDateTime.of(2001, 10, 23, 20, 45),null, false));
        advertisements.add(new Advertisement( "Backpack", "This is test 3 for advertisements", 100, "Tehran", "abc, def, ghi", "number",LocalDateTime.of(2000, 10, 23, 22, 45),null, true));
        advertisements.add(new Advertisement("Car", "This is test 4 for advertisements", 5000, "Tabriz", "abc, def, ghi", "number", LocalDateTime.of(2001, 4, 23, 14, 45),null,  false));
        advertisements.add(new Advertisement("Towel", "This is test 5 for advertisements", 4.5, "Mashhad", "abc, def, ghi", "number", LocalDateTime.of(2001, 10, 8, 21, 45),null, false));
        advertisements.add(new Advertisement("Ball", "This is test 6 for advertisements", 3.99, "Shiraz", "abc, def, ghi", "number",LocalDateTime.of(2000, 2, 12, 18, 0),null, true));
        dataHolder.advertisements = advertisements;*/
        ClientHandler.setData(dataHolder);
        while (!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(new ClientHandler(socket));
            thread.start();
            System.out.println("New client connected");
        }

    }
}
