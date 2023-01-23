
public class DivarClient {
    public static void main(String[] args) throws Exception {
//        int port = 8357;
//
//        new Thread(new DivarServer(port)).start();
//
//        Socket socket = new Socket("localhost", port);
//        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//        DataInputStream dis = new DataInputStream(socket.getInputStream());
//        dos.writeUTF("rtgrt");
//        dos.flush();
//        String response = dis.readUTF();
//        System.out.println(response);
//        dos.close();
//        socket.close();
          User foad = new User("sadaf.aghili", "123aaxsdsds", "sadafaghili@gmail.com");
          DataBase.addUser(foad);
          System.out.println(DataBase.getUsers().get(0).getUsername());
          System.out.println(DataBase.getUsers().get(0).getPassword());
          System.out.println(DataBase.getUsers().get(0).getEmail());
    }
}
