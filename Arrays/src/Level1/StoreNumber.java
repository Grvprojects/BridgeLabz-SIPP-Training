package Level1;

import java.util.Scanner;

class StoreNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] numbers = new double[10];
        double total = 0.0;
        int index = 0;

        while (true) {
            double num = sc.nextDouble();
            if (num <= 0 || index == 10) break;
            numbers[index++] = num;
        }

        for (int i = 0; i < index; i++) {
            total += numbers[i];
        }
        System.out.println("Sum of numbers: " + total);
    }
}
