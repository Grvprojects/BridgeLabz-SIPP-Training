package Level1;

import java.util.Scanner;

public class StringOutOfIndexException {
    public static void generateException(String s) {
        System.out.println("Character at index 10: " + s.charAt(10));
    }
    public static void handleException(String s) {
        try {
            System.out.println("Character at index 10: " + s.charAt(10));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Caught StringIndexOutOfBoundsException: Index is out of bounds.");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        generateException(s);
        handleException(s);

    }
}
