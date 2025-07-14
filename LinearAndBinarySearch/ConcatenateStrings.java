
public class ConcatenateStrings {
    public static String concatenate(String[] arr) {
        StringBuffer sb = new StringBuffer();
        for (String s : arr) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] input = {"Java", "is", "fun"};
        System.out.println(concatenate(input)); // Output: Javaisfun
    }
}
