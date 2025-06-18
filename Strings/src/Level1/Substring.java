package Level1;

import java.util.Scanner;

public class Substring {
    public static boolean check(String a, String b){
        return a.equals(b);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int startIdx = sc.nextInt();
        int endIdx = sc.nextInt();

        String sub = SubstringGenerator(s,startIdx,endIdx);

        String builtSub = s.substring(startIdx, endIdx);
        System.out.println("Susbtring genrated form startIdx" + startIdx + " to endIdx " + endIdx + " is " + sub);
        System.out.println(check(sub,builtSub));
    }

    private static String SubstringGenerator(String s, int startIdx, int endIdx) {
        String sub = "";
        for (int i = startIdx; i < endIdx; i++) {
            sub += s.charAt(i);
        }
        return sub;
    }
}
