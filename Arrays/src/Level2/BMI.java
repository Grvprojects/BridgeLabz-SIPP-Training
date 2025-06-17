package Level2;

import java.util.Scanner;

public class BMI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfPersons = sc.nextInt();
        double[] weights = new double[numberOfPersons];
        double[] heights = new double[numberOfPersons];
        double[] bmis = new double[numberOfPersons];
        String[] statuses = new String[numberOfPersons];
        for (int i = 0; i < numberOfPersons; i++) {
            weights[i] = sc.nextDouble();
            heights[i] = sc.nextDouble();
        }
        for (int i = 0; i < numberOfPersons; i++) {
            bmis[i] = weights[i] / (heights[i] * heights[i]);
            if (bmis[i] <= 18.4) {
                statuses[i] = "Underweight";
            } else if (bmis[i] >= 18.5 && bmis[i] <= 24.9) {
                statuses[i] = "Normal";
            } else if (bmis[i] >= 25.0 && bmis[i] <= 39.9) {
                statuses[i] = "Overweight";
            } else if (bmis[i] >= 40.0) {
                statuses[i] = "Obese";
            }
        }
        for (int i = 0; i < numberOfPersons; i++) {
            System.out.print(statuses[i] + " ");
        }
    }
}
