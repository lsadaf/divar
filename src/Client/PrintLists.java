package Client;

import CLI.Colors;
import DataTypes.Advertisement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class PrintLists {
    public static void printAllAdList (ArrayList<Advertisement> input){
        if ( input.isEmpty()){
            System.out.println(Colors.colorPrint(Colors.RED, "THERE ARE NO ADS"));
            return;
        }
        for ( Advertisement a : input){
            if ( a.isPrioritized()){
                System.out.println(a);
                System.out.println(Colors.colorPrint(Colors.PURPLE_BRIGHT, "--------------"));
            }
        }
        for ( Advertisement a : input){
            if ( !a.isPrioritized()){
                System.out.println(a);
                System.out.println(Colors.colorPrint(Colors.CYAN_BRIGHT, "--------------"));
            }
        }
    }
    public static void printSortedByPrice (ArrayList<Advertisement> input, boolean ascending){
        input.sort(new Comparator<Advertisement>() {
            public int compare(Advertisement o1, Advertisement o2) {
                if (o1.getPrice() == o2.getPrice())
                    return 0;
                return o1.getPrice() < o2.getPrice() ? -1 : 1;
            }
        });
        if ( !ascending){
            Collections.reverse(input);
        }
        for (Advertisement a : input){
            System.out.println(a);
            System.out.println(Colors.colorPrint(Colors.CYAN_BRIGHT, "--------------"));
        }
    }
    public static void printAdByPriceRange(ArrayList<Advertisement> input, double n1, double n2 ){
        input.sort(new Comparator<Advertisement>() {
            public int compare(Advertisement o1, Advertisement o2) {
                if (o1.getPrice() == o2.getPrice())
                    return 0;
                return o1.getPrice() < o2.getPrice() ? -1 : 1;
            }
        });
        for ( Advertisement advertisement : input){
            if ( advertisement.getPrice() >= n1 && advertisement.getPrice()<= n2){
                System.out.println(advertisement);
                System.out.println(Colors.colorPrint(Colors.CYAN_BRIGHT, "--------------"));
            }
        }
    }
    public static void printAdByUploadDate(ArrayList<Advertisement> input){
        input.sort(new Comparator<Advertisement>() {
            public int compare(Advertisement o1, Advertisement o2) {
                if (o1.getLastUpdate().compareTo(o2.getLastUpdate()) > 0){
                    return -1;
                }
                if (o1.getLastUpdate().compareTo(o2.getLastUpdate()) < 0){
                    return 1;
                }
                return o1.getLastUpdate().compareTo(o2.getLastUpdate());
            }
        });
        for (Advertisement a : input){
            System.out.println(a);
            System.out.println(Colors.colorPrint(Colors.CYAN_BRIGHT, "--------------"));
        }
    }
    public static void printAdByCity(ArrayList<Advertisement> input, String city){
        int count = 0;
        for (Advertisement a : input){
            if (a.getCity().equals(city)){
                count++;
                System.out.println(a);
                System.out.println(Colors.colorPrint(Colors.CYAN_BRIGHT, "--------------"));
            }
        }
        if ( count == 0){
            System.out.println(Colors.colorPrint(Colors.RED, "NO ADVERTISEMENT IN GIVEN CITY EXISTS"));
        }
    }
    public static void printAdBySearch (ArrayList<Advertisement> input, String search){
        int count = 0;
        for (Advertisement a : input){
            if (a.getName().contains(search)){
                count++;
                System.out.println(a);
                System.out.println(Colors.colorPrint(Colors.CYAN_BRIGHT, "--------------"));
            }
        }
        if ( count == 0){
            System.out.println(Colors.colorPrint(Colors.RED, "NO ADVERTISEMENT EXISTS"));
        }
    }
    public static boolean printAdDetails (ArrayList<Advertisement> input, String id){
        for (Advertisement advertisement : input){
            if (advertisement.getAdvertisementID().equals(id)){
                advertisement.printDetails();
                return true;
            }
        }
        return false;
    }
}
