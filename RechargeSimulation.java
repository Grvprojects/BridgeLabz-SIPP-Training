
import java.util.Scanner;

public class RechargeSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double currentBalance = 0;
        int simChoice;
        int planChoice;
        char continueRecharge;

        System.out.println("Welcome to Recharge Simulation");
         System.out.println("Select your SIM:");
        System.out.println("1. Jio");
        System.out.println("2. Airtel");
        System.out.println("3. VI");
          simChoice = sc.nextInt();
      
        if (simChoice == 1 || simChoice == 2 || simChoice == 3) {
            if (simChoice == 1) {
                System.out.println("You selected Jio.");
               
            } else if (simChoice == 2) {
                System.out.println("You selected Airtel.");
               

            } else if (simChoice==3) {
                System.out.println("You selected VI.");
                
            }
            else {
                System.out.println("Do another recharge ");
            }
        }
         do {
                System.out.println("Choose a plan:");
                System.out.println("1. ₹149 - 28 Days - 1GB/day");
                System.out.println("2. ₹199 - 28 Days - 1.5GB/day");
                System.out.println("3. ₹239 - 28 Days - 2GB/day");
                planChoice = sc.nextInt();

                if (planChoice == 1) {
                    currentBalance += 149;
                    System.out.println("You selected ₹149 plan.");
                } else if (planChoice == 2) {
                    currentBalance += 199;
                    System.out.println("You selected ₹199 plan.");
                } else if (planChoice == 3) {
                    currentBalance += 239;
                    System.out.println("You selected ₹239 plan.");
                } else {
                    System.out.println("Invalid plan choice.");
                }
            
                System.out.println("Your current amount:" + currentBalance);
                System.out.println("Do you want another recharge (y/n)");
                continueRecharge = sc.next().charAt(0);
         }
            
        while (continueRecharge == 'y');
        {

            System.out.println("Final amount:" + currentBalance);
        } 
    }
}


