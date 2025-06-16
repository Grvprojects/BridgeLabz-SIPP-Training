package Level1;

import java.util.*;

public class CountDownFor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        for(int i = count; i >= 1; i--) {
            System.out.println(i);
        }
    }

}
