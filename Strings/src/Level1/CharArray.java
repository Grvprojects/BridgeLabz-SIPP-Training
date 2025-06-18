package Level1;

import java.util.Scanner;

public class CharArray {
    public static char[] getCharArray(String s) {
        char[] result = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            result[i] = s.charAt(i);
        }
        return result;
    }
    public static boolean CompareCharArray(char[] arr1, char[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] arr = getCharArray(s);
        char[] arr2 = s.toCharArray();
        for(char c:arr){
            System.out.print(c + " ");
        }
        System.out.println();
        System.out.println(CompareCharArray(arr,arr2));
    }
}
