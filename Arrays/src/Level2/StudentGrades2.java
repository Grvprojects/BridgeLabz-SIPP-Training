package Level2;

import java.util.Scanner;

public class StudentGrades2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfStudents = sc.nextInt();
        int [][]marks = new int[numberOfStudents][3];
        double []percentages = new double[numberOfStudents];
        String []grades = new String[numberOfStudents];
        for (int i = 0; i < numberOfStudents; i++) {
            for (int j = 0; j < 3; j++) {
                String subject = (j == 0) ? "Physics" : (j == 1) ? "Chemistry" : "Maths";
                System.out.print(subject + " marks: ");
                marks[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < numberOfStudents; i++) {
            int totalMarks = marks[i][0] + marks[i][1] + marks[i][2];
            percentages[i] = totalMarks / 3.0;
            if (percentages[i] >= 90) grades[i] = "A";
            else if (percentages[i] >= 80) grades[i] = "B";
            else if (percentages[i] >= 70) grades[i] = "C";
            else if (percentages[i] >= 60) grades[i] = "D";
            else if (percentages[i] >= 50) grades[i] = "E";
            else grades[i] = "R";
        }
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println((i + 1) + "\t" + marks[i][0] + "\t" + marks[i][1] + "\t" + marks[i][2] + "\t" + String.format("%.2f%%", percentages[i]) + "\t" + grades[i]);
        }

    }
}
