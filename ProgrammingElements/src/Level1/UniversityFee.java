package Core_Java.Day1.Level1;

public class UniversityFee {
    public static void main(String[] args) {
        int fee_charged = 125000;
        int discount_in_percent = 10;
        int discounted_amount = (fee_charged * discount_in_percent)/100;
        int payable_Amount = fee_charged - discounted_amount;
        System.out.println("The discount amount is INR :" + discounted_amount + " and final discounted fees is INR : "+ payable_Amount);
    }
}
