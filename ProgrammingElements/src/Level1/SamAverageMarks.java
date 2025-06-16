package Core_Java.Day1.Level1;

public class SamAverageMarks {
    public static void main(String[] args){
        int Sam_Math_marks = 94;
        int Sam_Physics_marks = 95;
        int Sam_Chemistry_marks = 96;
        int total_real_marks = 300;
        int total_Marks = Sam_Physics_marks + Sam_Math_marks + Sam_Chemistry_marks;
        double Average = ((double)total_Marks/total_real_marks);
        double percent = 100*Average;
        System.out.println("Average Percent Marks In PCM: " + percent);
    }

}
