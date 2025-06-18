package Level1;

import java.util.Scanner;

public class UpperToLowerCase {
    public static String CustomConversion(String s) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                result.append((char)(ch + 32));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
    public static boolean compareStrings(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String builtInLower = s.toLowerCase();
        String customLower = CustomConversion(s);
        boolean isEqual = compareStrings(builtInLower, customLower);

        System.out.println("Built-in Uppercase: " + builtInLower);
        System.out.println("Custom Uppercase: " + customLower);
        System.out.println("both uppercase strings equal? " + isEqual);
    }
}
