package Level1;

import java.util.Scanner;

public class NaturalNumberSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n > 0){
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += i;
            }
            int formulaSum = n * (n + 1) / 2;
            if (sum == formulaSum) {
                System.out.println(sum);
            }
        }
        else{
            System.out.println("Not a Natural Number");
        }

    }
}
