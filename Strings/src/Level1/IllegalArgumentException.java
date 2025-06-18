package Level1;

import java.util.Scanner;

public class IllegalArgumentException {
    public static void generateException(String s) {
        System.out.println("Substring: " + s.substring(5, 2));
    }

    public static void handleException(String s) {
        try {
            System.out.println("Substring: " + s.substring(5, 2));
        } catch (java.lang.IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException: Start index is greater than end index.");
        } catch (RuntimeException e) {
            System.out.println("Caught generic RuntimeException: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        generateException(s);
        handleException(s);
    }
}
