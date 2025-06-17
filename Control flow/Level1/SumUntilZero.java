package Level1;

import java.util.*;

public class SumUntilZero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double total = 0;
        double num;
        while ((num = sc.nextDouble()) != 0) {
            total += num;
        }

        System.out.println("Total sum: " + total);

    }
}
