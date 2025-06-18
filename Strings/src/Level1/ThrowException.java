package Level1;

import java.util.Scanner;

public class ThrowException {
    public static void generateException() {
        String s = null;
        System.out.println("Text length: " + s.length());
    }

    public static void handleException() {
        String text = null;

        try {
            System.out.println("Text length: " + text.length());
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: text is null.");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        generateException();
        handleException();
    }
}
