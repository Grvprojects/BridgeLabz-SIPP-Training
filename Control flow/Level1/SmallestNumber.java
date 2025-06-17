package Level1;

import java.util.*;

public class SmallestNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();

        if(num1 < num2 && num1 < num3){
            System.out.println("The number" + num1 + "is the smallest number");
        }
    }
}
