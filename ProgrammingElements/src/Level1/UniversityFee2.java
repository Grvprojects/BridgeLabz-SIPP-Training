package Level1;
import java.util.*;
public class UniversityFee2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int fee = sc.nextInt();
        int discountPercent = sc.nextInt();
        int discount = (fee*discountPercent)/100;
        int payable = fee - discount;
        System.out.print("The discount amount is INR : "+discount + " and final discounted fee is INR "+ payable);

    }
}