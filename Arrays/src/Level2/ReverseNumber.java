package Level2;

import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int digitCount = 0;
        int temp = number;
        while (temp > 0) {
            digitCount++;
            temp /= 10;
        }
        int[] digits = new int[digitCount];
        int index = 0;
        while (number > 0) {
            digits[index] = number % 10;
            number /= 10;
            index++;
        }
        int[] reversedDigits = new int[digitCount];
        for (int i = 0; i < digitCount; i++) {
            reversedDigits[i] = digits[i];
        }
        for (int i = 0; i < digitCount; i++) {
            System.out.print(reversedDigits[i]);
        }
    }
}
