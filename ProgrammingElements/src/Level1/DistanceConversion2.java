package Level1;

import java.util.Scanner;

public class DistanceConversion2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double feet = sc.nextDouble();
        double yards = feet / 3;
        double miles = yards / 1760;
        System.out.println("Distance in feet: " + feet);
        System.out.println("Distance in yards: " + yards);
        System.out.println("Distance in miles: " + miles);
    }
}
