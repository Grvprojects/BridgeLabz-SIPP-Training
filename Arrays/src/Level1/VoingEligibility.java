package Level1;

import java.util.*;

public class VoingEligibility {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ages[] = new int[10];
        for (int i = 0; i < ages.length; i++) {
            ages[i] = sc.nextInt();
            if (ages[i] >= 18) {
                System.out.println("The student with the age " + ages[i] + " can vote.");
            } else {
                System.out.println("The student with the age " + ages[i] + " cannot vote.");
            }
        }
    }
}
