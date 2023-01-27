package Client;

import CLI.*;
import Communication.RequestPacket;
import Communication.RequestType;
import Communication.ResponsePacket;
import DataTypes.Advertisement;
import DataTypes.User;
import Exceptions.AdvertismentAlreadyExists;
import Exceptions.PhoneNumberInvalidException;
import Server.DataBase;

import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ActionHandler {
    private final Scanner scanner;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;
    User user;
    //  ArrayList<Advertisement> test;

    public ActionHandler(Socket socket) throws IOException {
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        scanner = new Scanner(System.in);
    }


    //just for test
   /* public ActionHandler() {
        scanner = new Scanner(System.in);
    }*/

    //just for test
    /*public void setTest(ArrayList<Advertisement> test) {
        this.test = test;
    }*/

    public void start() throws Exception {
        firstMenu();
    }
    public void firstMenu() throws Exception {
        int input;
        JUI.clearScreen();
        System.out.print("1. SIGNUP\n2. LOGIN\n3. EXIT\n>> ");
        input = scanner.nextInt();
        scanner.nextLine();
        if (input == 1) {
            signupMenu();

        } else if (input == 2) {
            loginMenu();
        } else if (input == 3) {
            JUI.clearScreen();
            System.out.println(Colors.colorPrint(Colors.GREEN, "Good luck!"));
            return;

        } else {
            JUI.clearScreen();
            System.out.println(Colors.colorPrint(Colors.RED, "INVALID INPUT!"));

        }

    }
    public void printMenu() throws Exception {
        int input;
        JUI.clearScreen();
        while (true) {
            System.out.print("1. PROFILE\n2. ADVERTISEMENT\n3. EXIT\n>> ");
            input = scanner.nextInt();
            scanner.nextLine();
            if (input == 1) {
                printProfile();

            } else if (input == 2) {
                advertisementMenu();
            } else if (input == 3) {
                JUI.clearScreen();
                System.out.println(Colors.colorPrint(Colors.GREEN, "Good luck!"));
                return;

            } else {
                JUI.clearScreen();
                System.out.println(Colors.colorPrint(Colors.RED, "INVALID INPUT!"));

            }

        }
    }

    private void printProfile() throws Exception {
        JUI.clearScreen();
        while (true) {
            while (true) {
                JUI.changeColor(JUI.Colors.BOLD_GREEN);
                System.out.println("PROFILE");
                JUI.changeColor(JUI.Colors.DEFAULT);
                System.out.print("1. USERNAME: " + user.getUsername() + "\n2. NAME: " + user.getName() + "\n3. LASTNAME: " + user.getLastName());
                System.out.print("\n4. PHONE NUMBER: " + user.getPhoneNumber() + "\n5. EMAIL: " + user.getEmail());
                if (user.getPicture() != null) {
                    System.out.print("\n6. PICTURE: " + user.getPicture() + "\n7. EXIT\n");
                } else System.out.print("\n6. PICTURE: NO PICTURE\n");
                System.out.print(Colors.colorPrint(Colors.GREEN, "WHICH ONE TO CHANGE(ANY OTHER TO GO BACK)? \n"));
                System.out.print("\n>> ");
                int input = scanner.nextInt();
                JUI.clearScreen();
                boolean flag = true;
                while (flag) {
                    try {
                        switch (input) {
                            case 1:
                                System.out.print("CHANGE USERNAME: \n>> ");
                                String a = scanner.next();
                                user.setUsername(a);
                                flag = false;
                                break;
                            case 2:
                                System.out.print("CHANGE NAME: \n>> ");
                                String b = scanner.next();
                                user.setName(b);
                                flag = false;
                                break;
                            case 3:
                                System.out.print("CHANGE LASTNAME: \n>> ");
                                String c = scanner.next();
                                user.setLastName(c);
                                flag = false;
                                break;
                            case 4:
                                System.out.print("CHANGE PHONE NUMBER: \n>> ");
                                String d = scanner.next();
                                user.setPhoneNumber(d);
                                flag = false;
                                break;
                            case 5:
                                System.out.print("CHANGE EMAIL: \n>> ");
                                String e = scanner.next();
                                user.setEmail(e);
                                flag = false;
                                break;
                            case 6:
                                System.out.print("INSERT PICTURE: \n>> ");
                                String f = scanner.next();
                                user.setPicture(f);
                                flag = false;
                                break;
                            default:
                                return;
                        }
                    } catch (Exception e) {
                        JUI.clearScreen();
                        JUI.changeColor(JUI.Colors.RED);
                        System.out.println(e.getMessage());
                        JUI.sleep(3000);
                        JUI.clearScreen();
                        JUI.changeColor(JUI.Colors.DEFAULT);
                    }
                }
            }

        }
    }

    public void signupMenu() throws Exception {
        JUI.clearScreen();
        boolean f= true;
        while (f) {
            while (true) {
                JUI.changeColor(JUI.Colors.BOLD_GREEN);
                System.out.println("SIGNUP");
                JUI.changeColor(JUI.Colors.DEFAULT);
                System.out.print("USERNAME: \n>> ");
                String username = scanner.nextLine();
                System.out.print("PASSWORD: \n>> ");
                String password = scanner.nextLine();
                System.out.print("EMAIL: \n>> ");
                String email = scanner.nextLine();
                try {
                    user = new User(username, password, email);
                    DataBase.addUser(user);
                    f = false;
                    break;

                } catch (Exception e) {
                    JUI.clearScreen();
                    JUI.changeColor(JUI.Colors.RED);
                    System.out.println(e.getMessage());
                    JUI.sleep(3000);
                    JUI.clearScreen();
                    JUI.changeColor(JUI.Colors.DEFAULT);
                }
            }
            printMenu();

        }
    }
    public void loginMenu() throws Exception {
        JUI.clearScreen();
        boolean f = true;
        while (f) {
            while (true) {
                JUI.changeColor(JUI.Colors.BOLD_GREEN);
                System.out.println("LOGIN");
                JUI.changeColor(JUI.Colors.DEFAULT);
                System.out.print("USERNAME: \n>> ");
                String username = scanner.nextLine();
                System.out.print("PASSWORD: \n>> ");
                String password = scanner.nextLine();
                try {
                    user = User.login(username,password);
                    f = false;
                    break;

                } catch (Exception e) {
                    JUI.clearScreen();
                    JUI.changeColor(JUI.Colors.RED);
                    System.out.println(e.getMessage());
                    JUI.sleep(3000);
                    JUI.clearScreen();
                    JUI.changeColor(JUI.Colors.DEFAULT);
                }
            }
            printMenu();

        }
    }
    public void advertisementMenu () throws AdvertismentAlreadyExists {
        int input;
        while(true){
            JUI.clearScreen();
            System.out.print("1. ADVERTISEMENT LIST\n2. ADD NEW ADVERTISEMENT\n3. BACK\n>> ");
            input = scanner.nextInt();
            scanner.nextLine();
            JUI.clearScreen();
            if ( input == 1){
                ArrayList<Advertisement> mainList = DataBase.getAds();
                ArrayList<Advertisement> adList = mainList;
                PrintLists.printAllAdList(adList);
                while (true){
                    System.out.print("1. SORT\n2. FILTER PRICE RANGE\n3. FILTER CITY\n4. SEARCH\n5. VIEW DETAILS\n6. RESET FILTERS\n7. BACK\n>> ");
                    int input1 = scanner.nextInt();
                    scanner.nextLine();
                    if ( input1 == 1){
                        while (true){
                            JUI.clearScreen();
                            System.out.print("SORT\n-----------\n1. BY PRICE\n2. BY UPDATE DATE\n3. BACK\n>> ");
                            int input2 = scanner.nextInt();
                            scanner.nextLine();
                            if ( input2 == 1){
                                System.out.print("1. ASCENDING\n2. DESCENDING\n>> ");
                                int input3 = scanner.nextInt();
                                scanner.nextLine();
                                JUI.clearScreen();
                                if ( input3 == 1){
                                    PrintLists.printSortedByPrice(adList, true);
                                    break;
                                }
                                else if (input3 == 2){
                                    PrintLists.printSortedByPrice(adList, false);
                                    break;
                                }
                            }
                            else if ( input2 == 2){
                                JUI.clearScreen();
                                PrintLists.printAdByUploadDate(adList);
                                break;
                            }
                            else if ( input2 == 3){
                                JUI.clearScreen();
                                PrintLists.printAllAdList(adList);
                                break;
                            }
                        }

                    }
                    else if ( input1 == 2){
                        JUI.clearScreen();
                        System.out.print("PRICE RANGE\n" + Colors.colorPrint(Colors.CYAN, "-----------") + "\nENTER PRICE 1\n>> ");
                        int num1 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("ENTER PRICE 2\n>> ");
                        int num2 = scanner.nextInt();
                        scanner.nextLine();
                        JUI.clearScreen();
                        PrintLists.printAdByPriceRange(adList, num1, num2);
                    }
                    else if ( input1 == 3){
                        JUI.clearScreen();
                        System.out.print("ENTER CITY\n>> ");
                        String string = scanner.nextLine();
                        JUI.clearScreen();
                        PrintLists.printAdByCity(adList, string.toUpperCase());
                    }
                    else if ( input1 == 4){
                        JUI.clearScreen();
                        System.out.print("SEARCH NAME\n>> ");
                        String string = scanner.nextLine();
                        JUI.clearScreen();
                        PrintLists.printAdBySearch(adList, string);

                    }
                    else if ( input1 == 5){
                        System.out.print("ENTER ADS ID\n>> ");
                        String ads_id = scanner.nextLine();
                        JUI.clearScreen();
                        if ( PrintLists.printAdDetails(adList, ads_id)){
                            System.out.print("1. ADD TO FAVORITES\n2. BACK\n>> ");
                            int input4 = scanner.nextInt();
                            scanner.nextLine();
                            if ( input4 == 1){
                                RequestPacket requestPacket1 = new RequestPacket(RequestType.ADD_AD_TO_FAVORITES, ads_id);
                                sendRequest(requestPacket1);
                            }
                        }
                        JUI.clearScreen();
                        PrintLists.printAllAdList(adList);
                    }
                    else if ( input1 == 6){
                        adList = mainList;
                        JUI.clearScreen();
                        PrintLists.printAllAdList(adList);

                    }
                    else if (input1 == 7){
                        JUI.clearScreen();
                        break;
                    }
                }
            }
            else if ( input == 2){
                System.out.println("ADD NEW ADVERTISEMENT\n" + Colors.colorPrint(Colors.CYAN,"--------"));
                String name, city, description, location, phone;
                double price;
                System.out.print("ENTER ADS NAME\n>> ");
                name = scanner.nextLine();
                System.out.print("ENTER ADS DESCRIPTION\n>> ");
                description = scanner.nextLine();
                System.out.print("ENTER ITEMS PRICE\n>> ");
                price = Double.parseDouble(scanner.nextLine());
                System.out.print("ENTER YOUR CITY\n>> ");
                city = scanner.nextLine();
                System.out.print("ENTER YOUR ADDRESS\n>> ");
                location = scanner.nextLine();
                System.out.print("ENTER YOUR NUMBER\n>> ");
                phone = scanner.nextLine();
                int numOfPics;
                System.out.print("ENTER NUMBER OF PICS YOU WANT TO CHOOSE\n>> ");
                numOfPics = scanner.nextInt();
                scanner.nextLine();
                String[] pics = new String[numOfPics];
                for ( int i = 0 ; i < numOfPics ; i++){
                    FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
                    dialog.setMode(FileDialog.LOAD);
                    dialog.setVisible(true);
                    String file = dialog.getFile();
                    dialog.dispose();
                    pics[i] = file;
                }
                Advertisement advertisement = new Advertisement(name, description, price, city, location, phone, System.currentTimeMillis() / 1000L,pics, false);
                advertisement.setAdvertisementID(String.valueOf(DataBase.getAds().size() + 1));
                DataBase.addAdvertisement(advertisement);
                RequestPacket requestPacket = new RequestPacket(RequestType.NEW_AD, advertisement);
                sendRequest(requestPacket);
                //test.add(advertisement); for test
                JUI.clearScreen();
                System.out.println(Colors.colorPrint(Colors.GREEN_BRIGHT, "ADVERTISEMENT WAS CREATED SUCCESSFULLY"));
            }
            else if ( input == 3){
                JUI.clearScreen();
                break;

            }
            else{
                JUI.clearScreen();
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return responsePacket;
    }
}