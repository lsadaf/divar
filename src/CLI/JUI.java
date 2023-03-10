package CLI;

import java.io.*;
import java.util.Scanner;

public final class JUI {
    private int ROWS = -1;
    private int COLUMNS = -1;
    private static boolean isBold = false;
    private static boolean isUnderlined = false;
    private static boolean isStrikethrough = false;
    private static boolean isItalic = false;


    public enum Colors{
        DEFAULT,
        BLACK, BOLD_BLACK,
        RED, BOLD_RED,
        GREEN, BOLD_GREEN,
        YELLOW, BOLD_YELLOW,
        BLUE,  BOLD_BLUE,
        MAGENTA, BOLD_MAGENTA,
        CYAN, BOLD_CYAN,
        GRAY, BOLD_GRAY,
        WHITE, BOLD_WHITE
    }

    public enum Position{
        UPPER_LEFT, UPPER_CENTER, UPPER_RIGHT,
        CENTER_LEFT, CENTER, CENTER_RIGHT,
        LOWER_LEFT, LOWER_CENTER, LOWER_RIGHT
    }

    public JUI() throws IOException {
        initial();
    }

    public JUI(int rows, int columns) {
        ROWS = rows;
        COLUMNS = columns;
    }

    public void initial() throws IOException {
        if (ROWS != -1 && COLUMNS != -1) {
            return;
        }
        File f = new File("/tmp/terminal_size.txt");
        Scanner myReader;
        try {
            myReader = new Scanner(f);
            ROWS = 0;
            COLUMNS = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parameters = data.split(" ");
                ROWS = Integer.parseInt(parameters[0]);
                for (int i = 0; i < parameters[1].length(); i++) {
                    COLUMNS *= 10;
                    COLUMNS += Integer.parseInt(parameters[1].charAt(i) + "");
                }
            }
        } catch (FileNotFoundException e) {
            ROWS = 20;
            COLUMNS = 80;
        }

        clearScreen();
        int input = 0;
        while (input != 13) {

            for (int x = 0; x < ROWS; x++) {
                changeCursorPosition(x, COLUMNS);
                customPrint("┆", Colors.CYAN);
            }

            for (int y = 0; y < COLUMNS; y++) {
                changeCursorPosition(ROWS, y);
                customPrint("-", Colors.CYAN);
            }

            customPrint("Put the sign symbol in the lower right corner\nThen press ENTER", Position.CENTER, Colors.BOLD_YELLOW);
            customPrint(ROWS + " * " + COLUMNS, Position.LOWER_RIGHT, Colors.BOLD_GREEN);
            changeCursorPosition(ROWS,COLUMNS);
            customPrint("+", Colors.BOLD_CYAN);
            changeCursorPosition(0,0);

            input = getInput();
            clearScreen();

            if (input == 66) ROWS++;
            else if (input == 67) COLUMNS++;
            else if (input == 68) COLUMNS--;
            else if (input == 65) ROWS--;

            System.out.flush();

        }
        ROWS++;
        COLUMNS++;
        clearScreen();
    }

    public static void changeColor(int r, int g, int b) {
        if (0 <= r && r <= 255 && 0 <= g && g <= 255 && 0 <= b && b <= 255) {
            System.out.printf("\033[38;2;%d;%d;%dm", r, g, b);
            System.out.flush();
        }
    }


    public static void changeColor(Colors color) {
        switch (color) {
            case DEFAULT -> System.out.print("\033[0m");
            case BLACK -> System.out.print("\033[0;30m");
            case BOLD_BLACK -> System.out.print("\033[1;30m");
            case RED -> System.out.print("\033[0;31m");
            case BOLD_RED -> System.out.print("\033[1;31m");
            case GREEN -> System.out.print("\033[0;32m");
            case BOLD_GREEN -> System.out.print("\033[1;32m");
            case YELLOW -> System.out.print("\033[0;33m");
            case BOLD_YELLOW -> System.out.print("\033[1;33m");
            case BLUE -> System.out.print("\033[0;34m");
            case BOLD_BLUE -> System.out.print("\033[1;34m");
            case MAGENTA -> System.out.print("\033[0;35m");
            case BOLD_MAGENTA -> System.out.print("\033[1;35m");
            case CYAN -> System.out.print("\033[0;36m");
            case BOLD_CYAN -> System.out.print("\033[1;36m");
            case GRAY -> System.out.print("\033[0;37m");
            case BOLD_GRAY -> System.out.print("\033[1;37m");
            case WHITE -> System.out.print("\033[0;38m");
            case BOLD_WHITE -> System.out.print("\033[1;38m");
        }
        System.out.flush();
    }

    public int getColumns() throws IOException {
        return COLUMNS;
    }

    public int getRows() {
        return ROWS;
    }

    public static int getInput() throws IOException {
        return System.in.read();
    }

    public static void sleep(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }


    public static void customPrint(String string, Colors color) {
        changeColor(color);
        System.out.print(string);
        changeColor(Colors.DEFAULT);
    }

    public void customPrint(String string, Position position) {
        String[] lines = string.split("\n");
        int length = 0;
        for (String line : lines)
            if (line.length() > length)
                length = line.length();
        int width = string.split("\n").length;
        if (COLUMNS >= length && ROWS >= width) {
            for (int i = 0; i < string.split("\n").length; i++) {
                length = lines[i].length();
                switch (position) {
                    case UPPER_LEFT -> {
                        changeCursorPosition(i + 1, 0);
                        System.out.print(lines[i]);
                    }
                    case UPPER_CENTER -> {
                        changeCursorPosition(i + 1, (COLUMNS - length) / 2);
                        System.out.print(lines[i]);
                    }
                    case UPPER_RIGHT -> {
                        changeCursorPosition(i + 1, COLUMNS - length);
                        System.out.print(lines[i]);
                    }
                    case CENTER_LEFT -> {
                        changeCursorPosition(((ROWS - width) / 2) + i, 0);
                        System.out.print(lines[i]);
                    }
                    case CENTER -> {
                        changeCursorPosition(((ROWS - width) / 2) + i, (COLUMNS - length) / 2);
                        System.out.print(lines[i]);
                    }
                    case CENTER_RIGHT -> {
                        changeCursorPosition(((ROWS - width) / 2) + i, COLUMNS - length);
                        System.out.print(lines[i]);
                    }
                    case LOWER_LEFT -> {
                        changeCursorPosition(ROWS - (width - i), 0);
                        System.out.print(lines[i]);
                    }
                    case LOWER_CENTER -> {
                        changeCursorPosition(ROWS - (width - i), (COLUMNS - length) / 2);
                        System.out.print(lines[i]);
                    }
                    case LOWER_RIGHT -> {
                        changeCursorPosition(ROWS - (width - i), COLUMNS - length);
                        System.out.print(lines[i]);
                    }
                }
            }
        }
    }


    public void customPrint(String string, Position position, Colors color) throws IOException {
        changeColor(color);
        customPrint(string, position);
        changeColor(Colors.DEFAULT);
    }


    public void customPrint(String string, int row, int col) {
        saveCursor();
        changeCursorPosition(row, col);
        System.out.print(string);
        restoreCursor();
    }


    public void drawRectangle(int height, int width, Colors color, Position position) throws IOException {
        String rectangle = "";
        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) rectangle += " ";
            rectangle += "\n";
        }
        changeBackgroundColor(color);
        customPrint(rectangle, position);
        changeBackgroundColor(Colors.DEFAULT);
    }


    public void drawRectangle(int height, int width, Colors color, int x, int y) throws IOException {
        changeBackgroundColor(color);
        for(int i = 0; i < height; i++) {
            changeCursorPosition(y + i, x);
            for (int j = 0; j < width; j++)
                System.out.print(" ");
        }
        changeBackgroundColor(Colors.DEFAULT);
    }


    public static void changeCursorPosition(int row, int col) {
        System.out.printf("\033[%d;%dH", row, col);
        System.out.flush();
    }


    public static void saveCursor() {
        System.out.println("\0337");
        System.out.flush();
    }


    public static void restoreCursor() {
        System.out.println("\0338");
        System.out.flush();
    }


    public static void cursorUp() {
        System.out.print("\033[1A");
        System.out.flush();
    }


    public static void cursorUp(int n) {
        System.out.printf("\033[%dA", n);
        System.out.flush();
    }

    public static void cursorDown() {
        System.out.print("\033[1B");
        System.out.flush();
    }


    public static void cursorDown(int n) {
        System.out.printf("\033[%dB", n);
        System.out.flush();
    }


    public static void cursorForward() {
        System.out.print("\033[1C");
        System.out.flush();
    }


    public static void cursorForward(int n) {
        System.out.printf("\033[%dC", n);
        System.out.flush();
    }


    public static void cursorBackward() {
        System.out.print("\033[1D");
        System.out.flush();
    }


    public static void cursorBackward(int n) {
        System.out.printf("\033[%dD", n);
        System.out.flush();
    }


    public static void playSound() {
        System.out.println("\07");
        System.out.flush();
    }


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static void clearLine() {
        System.out.print("\033[K");
        System.out.flush();
    }


    public static void changeBackgroundColor(Colors color) {
        switch (color) {
            case DEFAULT -> System.out.print("\033[0m");
            case BLACK, BOLD_BLACK -> System.out.print("\033[40m");
            case RED, BOLD_RED -> System.out.print("\033[41m");
            case GREEN, BOLD_GREEN -> System.out.print("\033[42m");
            case YELLOW, BOLD_YELLOW -> System.out.print("\033[43m");
            case BLUE, BOLD_BLUE -> System.out.print("\033[44m");
            case MAGENTA, BOLD_MAGENTA -> System.out.print("\033[45m");
            case CYAN, BOLD_CYAN -> System.out.print("\033[46m");
            case GRAY, BOLD_GRAY -> System.out.print("\033[47m");
        }
        System.out.flush();
    }


    public static void bold() {
        if (isBold) System.out.print("\033[0m");
        else System.out.print("\033[1m");
        isBold = !isBold;
        System.out.flush();
    }


    public static void italic() {
        if (isItalic) System.out.print("\033[0m");
        else System.out.print("\033[3m");
        isItalic = !isItalic;
        System.out.flush();
    }


    public static void underline() {
        if (isUnderlined) System.out.print("\033[0m");
        else System.out.print("\033[4m");
        isUnderlined = !isUnderlined;
        System.out.flush();
    }


    public static void strikethrough() {
        if (isStrikethrough) System.out.print("\033[0m");
        else System.out.print("\033[9m");
        isStrikethrough = !isStrikethrough;
        System.out.flush();
    }
}