package Level1;

import java.util.Scanner;

public class ArrayOutOfIndexExcep {
    public static void genrateException(String []s) {
        System.out.println(s[5]);
    }
    public static void handleException(String []s) {
        try {
            System.out.println("Name at index 5: " + s[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: Invalid index accessed.");
        } catch (RuntimeException e) {
            System.out.println("Caught RuntimeException: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = new String[3];
        for (int i = 0; i < s.length; i++) {
            s[i] = sc.next();
        }

        genrateException(s);
        handleException(s);
    }
}
