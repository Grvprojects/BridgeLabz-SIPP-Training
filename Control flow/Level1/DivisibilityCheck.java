package Level1;

import java.util.*;

public class DivisibilityCheck {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        if(number %5 ==0 ){
            System.out.println("The number "+ number + "is divisible by 5 ");
        }
        else{
            System.out.println("The number "+ number + "is not divisible by 5 ");
        }
    }
}
