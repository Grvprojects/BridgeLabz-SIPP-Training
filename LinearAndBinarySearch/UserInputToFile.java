
import java.io.*;

public class UserInputToFile {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            
            String input;
            while (true) {
                input = br.readLine();
                if (input.equalsIgnoreCase("exit")) break;
                bw.write(input);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
