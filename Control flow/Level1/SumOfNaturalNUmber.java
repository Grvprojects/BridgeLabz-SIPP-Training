package Level1;

import java.util.*;

public class SumOfNaturalNUmber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n > 0) {
            int sum = 0, i = 1;
            while (i <= n) {
                sum += i;
                i++;
            }
            int formulaSum = n * (n + 1) / 2;
            if(sum==formulaSum) {
                System.out.println("Sum of natural numbers is " + sum);
            }
        } else {
            System.out.println(n + "is not a natural number.");
        }
    }
}
