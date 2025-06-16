package Core_Java.Day1.Level1;

public class ProfitOrLoss {
    public static void main(String[] args){
        int cost_Price = 129;
        int selling_Price = 191;

        int profit = selling_Price - cost_Price;
        double Profit_Percent = (double)profit/cost_Price *100;
        System.out.println("Gain a profit of Rs." + profit +" with a profit percentage of " +Profit_Percent);


    }
}
