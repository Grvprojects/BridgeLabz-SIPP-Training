
import java.io.*;

public class WordCount {
    public static void main(String[] args) {
        String target = "java";
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.toLowerCase().split("\\W+");
                for (String word : words) {
                    if (word.equals(target)) count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Occurrences of \"" + target + "\": " + count);
    }
}
