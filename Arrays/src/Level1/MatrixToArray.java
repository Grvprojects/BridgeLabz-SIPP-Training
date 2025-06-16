package Level1;

import java.util.Scanner;

class MatrixToArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int columns = sc.nextInt();

        int[][] matrix = new int[rows][columns];
        int[] array = new int[rows * columns];

        int index = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = sc.nextInt();
                array[index++] = matrix[i][j];
            }

        for(int ele : array){
            System.out.print(ele + " ");
        }

    }
}
