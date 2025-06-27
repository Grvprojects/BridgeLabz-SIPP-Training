import java.util.Scanner;

public class LexicographicalComparison {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String str1 = sc.nextLine();
        System.out.print("Enter second string: ");
        String str2 = sc.nextLine();

        int minLength = Math.min(str1.length(), str2.length());
        boolean areEqual = true;

        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                areEqual = false;
                if (str1.charAt(i) < str2.charAt(i)) {
                    System.out.println("\"" + str1 + "\" comes before \"" + str2 + "\"");
                } else {
                    System.out.println("\"" + str2 + "\" comes before \"" + str1 + "\"");
                }
                break;
            }
        }

        if (areEqual) {
            if (str1.length() == str2.length())
                System.out.println("Strings are equal.");
            else if (str1.length() < str2.length())
                System.out.println("\"" + str1 + "\" comes before \"" + str2 + "\"");
            else
                System.out.println("\"" + str2 + "\" comes before \"" + str1 + "\"");
        }
    }
}
