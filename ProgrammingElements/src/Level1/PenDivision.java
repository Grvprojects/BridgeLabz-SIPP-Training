package Core_Java.Day1.Level1;

public class PenDivision {
    public static void main(String[] args) {
        int pens = 14;
        int Students = 3;
        int remaining_pens = pens % Students;
        int pens_per_Student = pens / Students;
        System.out.println("The Pens per Student is: " + pens_per_Student + " and the remaining Pens not distributed is: " + remaining_pens);
    }
}
