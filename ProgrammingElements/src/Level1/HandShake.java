package Level1;

import java.util.Scanner;

public class HandShake {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Number_of_Students = sc.nextInt();
        int handshakes = (Number_of_Students*(Number_of_Students -1))/2 ;
        System.out.println("The possible number of handshakes is: " + handshakes);
    }
}
