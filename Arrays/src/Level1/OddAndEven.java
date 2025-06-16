package Level1;

import java.util.Scanner;

public class OddAndEven {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        if (number <= 0) {
            System.out.println("Invalid Number");
            return;
        }

        int[] odd = new int[number / 2 + 1];
        int[] even = new int[number / 2 + 1];
        int oddIndex = 0, evenIndex = 0;

        for (int i = 1; i <= number; i++) {
            if (i % 2 == 0) {
                even[evenIndex++] = i;
            } else {
                odd[oddIndex++] = i;
            }
        }
        for (int i = 1; i <= odd.length; i++) {
            System.out.println("Odd: " + odd[i]);
        }
        for (int i = 1; i <= even.length; i++) {
            System.out.println("Even: " + even[i]);
        }

    }
}
