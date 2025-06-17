package Level2;

import java.util.Scanner;

public class AllFactorsWhile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 100;
        int i = 99;
        while(i > 0){
            if(n % i == 0){
                System.out.println(i + " ");
            }
            i--;
        }
    }
}
