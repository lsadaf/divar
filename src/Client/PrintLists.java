package Client;

import CLI.ClearScreen;
import CLI.Colors;
import DataTypes.Advertisement;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class PrintLists {
    public static void printAllAdList (ArrayList<Advertisement> input){
        ClearScreen.clearScreen();
        for ( Advertisement a : input){
            if ( a.isPrioritized()){
                System.out.println(a);
                System.out.println(Colors.colorPrint(Colors.CYAN_BRIGHT, "--------------"));
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
        printAllAdList(input);
    }
    public static void printAddByPriceRange(ArrayList<Advertisement> input, double n1, double n2 ){
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
    public static void printAddByUploadDate (ArrayList<Advertisement> input){
        input.sort(new Comparator<Advertisement>() {
            public int compare(Advertisement o1, Advertisement o2) {
                if (o1.getPrice() == o2.getPrice())
                    return 0;
                return o1.getLastUpdate().compareTo(o2.getLastUpdate());
            }
        });
    }
}
