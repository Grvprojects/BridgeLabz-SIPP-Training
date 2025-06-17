package Level2;

import java.util.Scanner;

public class PowerWhile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int base = sc.nextInt();
        int exponenet = sc.nextInt();
        int ans = 1;
        int i=1;
        while(i <= exponenet) {
            ans *= base;
            i++;
        }
        System.out.println(base + "to the power " + exponenet + " is = " + ans);
    }
}
