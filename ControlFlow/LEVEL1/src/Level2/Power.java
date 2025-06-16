package Level2;

import java.util.Scanner;

public class Power {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int base = sc.nextInt();
        int exponent = sc.nextInt();
        int ans = 1;
        for(int i=1;i<=exponent;i++){
            ans*=base;
        }
        System.out.println(base + "to the power " + exponent + " is = " + ans);
    }
}
