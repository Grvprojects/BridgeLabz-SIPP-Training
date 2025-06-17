package Level2;

import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfPersons = sc.nextInt();
        double[][] personData = new double[numberOfPersons][3];
        String[] weightStatus = new String[numberOfPersons];
        for (int i = 0; i < numberOfPersons; i++) {
            do {
                personData[i][1] = sc.nextDouble();
                if (personData[i][1] <= 0) {
                    System.out.println("Invalid input. Height must be positive.");
                }
            } while (personData[i][1] <= 0);
        }
        for (int i = 0; i < numberOfPersons; i++) {
            personData[i][2] = personData[i][0] / (personData[i][1] * personData[i][1]);
            if (personData[i][2] <= 18.4) {
                weightStatus[i] = "Underweight";
            } else if (personData[i][2] <= 24.9) {
                weightStatus[i] = "Normal";
            } else if (personData[i][2] <= 39.9) {
                weightStatus[i] = "Overweight";
            } else {
                weightStatus[i] = "Obese";
            }
        }
        for (int i = 0; i < numberOfPersons; i++) {
            System.out.println(weightStatus[i]+" ");
        }
    }
}
