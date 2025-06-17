package Level2;

import java.util.Scanner;

public class LargestNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int digits[] = new int[10];
        int idx = 0;
        while (n > 0) {
            digits[idx++] = n % 10;
            n /= 10;
            if (idx == digits.length) break;
        }
        int largest = 0, secondLargest = 0;
        for (int digit : digits) {
            if (digit > largest) {
                secondLargest = largest;
                largest = digit;
            } else if (digit > secondLargest && digit != largest) {
                secondLargest = digit;
            }
        }
        System.out.println("Largest Number is " + largest);
        System.out.println("Second Largest Number is " + secondLargest);
    }


}
