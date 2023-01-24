package Client;

import CLI.*;
import Communication.RequestPacket;
import Communication.RequestType;
import Communication.ResponsePacket;
import DataTypes.Advertisement;
import Server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ActionHandler {
    Scanner scanner;
    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public ActionHandler(Socket inputSocket) throws IOException {
        new Thread(new Server(inputSocket.getPort())).start();

        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        scanner = new Scanner(System.in);

    }

    public void start (){
        printMenu();

    }

    public void printMenu (){
        int input;
        ClearScreen.clearScreen();
        while (true){
            System.out.print("1. PROFILE\n2.ADVERTISEMENT\n3.EXIT>> ");
            input = scanner.nextInt();
            if ( input == 1){

            }
            else if ( input == 2){
                advertisementMenu();

            }
            else if ( input == 3){
                ClearScreen.clearScreen();
                System.out.println(Colors.colorPrint(Colors.GREEN, "Good luck!"));
                return;

            }
            else{
                ClearScreen.clearScreen();
                System.out.println(Colors.colorPrint(Colors.RED, "INVALID INPUT!"));

            }

        }
    }
    public void advertisementMenu (){
        int input;
        ClearScreen.clearScreen();
        while(true){
            System.out.print("1. ADVERTISEMENT LIST\n2. ADD NEW ADVERTISEMENT\n3. BACK\n>> ");
            input = scanner.nextInt();
            if ( input == 1){
                while (true){
                    RequestPacket requestPacket = new RequestPacket(RequestType.GET_ALL_ADS, null);
                    ResponsePacket responsePacket = getResponse();
                    PrintLists.printAllAdList((ArrayList<Advertisement>) responsePacket.getInitialData());
                    System.out.print("1. SORT\n2. PRICE RANGE\n3. BACK>> ");
                    int input1 = scanner.nextInt();
                    if ( input1 == 1){

                    }
                    else if ( input1 == 2){

                    }
                    else if (input1 == 3){
                        break;
                    }
                }
            }
            else if ( input == 2){

            }
            else if ( input == 3){
                ClearScreen.clearScreen();
                break;

            }
            else{
                ClearScreen.clearScreen();
                System.out.println(Colors.colorPrint(Colors.RED, "INVALID INPUT!"));

            }

        }
    }
    public void sendRequest (RequestPacket requestPacket){
        try {
            objectOutputStream.writeUnshared(requestPacket);
        } catch (IOException ignored) {
        }
    }
    public ResponsePacket getResponse (){
        ResponsePacket responsePacket = null;
        try {
            responsePacket = (ResponsePacket) objectInputStream.readUnshared();
        } catch (IOException | ClassNotFoundException ignored) {

        }
        return responsePacket;
    }
}
