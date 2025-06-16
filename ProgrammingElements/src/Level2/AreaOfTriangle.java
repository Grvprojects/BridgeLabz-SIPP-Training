package Level2;

import java.util.Scanner;

public class AreaOfTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double base = sc.nextDouble();
        double height = sc.nextDouble();
        double area_In_Inches = 0.5 * base * height;
        double area_In_Cm = area_In_Inches * 6.4516;

        System.out.println("Area of Triangle inches: " + area_In_Inches);
        System.out.println("Area in Triangle centimeters: " + area_In_Cm);
    }
}
