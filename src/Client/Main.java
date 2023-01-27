package Client;

import CLI.Colors;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static Socket socket;
    public static void main(String[] args) throws Exception {
        try {
            socket = new Socket("localhost", 4000);
        } catch (IOException e) {
            System.out.println(Colors.colorPrint(Colors.RED, "MAKE SURE SERVER IS CONNECTED"));
            System.exit(1);
        }
        ActionHandler actionHandler = new ActionHandler(socket);
        actionHandler.start();
      /*  ArrayList<Advertisement> advertisements = new ArrayList<>(); test
        advertisements.add(new Advertisement( "Book", "This is test 1 for advertisements", 18.99, "Tehran", "abc, def, ghi", "number", LocalDateTime.of(2000, 10, 23, 20, 45),null, false));
        advertisements.add(new Advertisement("Toy", "This is test 2 for advertisements", 25.60, "Shiraz", "abc, def, ghi", "number", LocalDateTime.of(2001, 10, 23, 20, 45),null, false));
        advertisements.add(new Advertisement( "Backpack", "This is test 3 for advertisements", 100, "Tehran", "abc, def, ghi", "number",LocalDateTime.of(2000, 10, 23, 22, 45),null, true));
        advertisements.add(new Advertisement("Car", "This is test 4 for advertisements", 5000, "Tabriz", "abc, def, ghi", "number", LocalDateTime.of(2001, 4, 23, 14, 45),null,  false));
        advertisements.add(new Advertisement("Towel", "This is test 5 for advertisements", 4.5, "Mashhad", "abc, def, ghi", "number", LocalDateTime.of(2001, 10, 8, 21, 45),null, false));
        advertisements.add(new Advertisement("Ball", "This is test 6 for advertisements", 3.99, "Shiraz", "abc, def, ghi", "number",LocalDateTime.of(2000, 2, 12, 18, 0),null, true));
*/
    }
}
