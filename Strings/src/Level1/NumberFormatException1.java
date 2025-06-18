package Level1;

import java.util.Scanner;

public class NumberFormatException1 {
    public static void generateException(String s) {
        int number = Integer.parseInt(s);
        System.out.println(number);

    }
    public static void handleException(String s) {
        try {
            int number = Integer.parseInt(s);
            System.out.println("Parsed number: " + number);
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException: Input is not a valid number.");
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
