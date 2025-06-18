package Level1;

import java.util.Scanner;

public class StringComparison {
    public static boolean check(String a,String b){
        boolean result1 = a.equals(b);
        boolean result2 = true;
        if(a.length() == b.length()){
            for(int i=0;i<a.length();i++){
                if(a.charAt(i)!=b.charAt(i)){
                    result2 = false;
                    break;
                }
            }
        }
        return result1 && result2;
    }
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();

        System.out.println(check(a,b));

    }
}
