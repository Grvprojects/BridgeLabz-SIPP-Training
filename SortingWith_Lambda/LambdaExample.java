import java.util.*;
import java.util.stream.Collectors;

public class LambdaExample {
    public static void main(String[] args) {
        
        List<String> names = Arrays.asList("Ravi", "Anu", "Kiran", "Megha");
        
        System.out.println("Original names: " + names);

      
        names.sort((a, b) -> a.length() - b.length());
        System.out.println("Sorted by length: " + names);

      
        names.sort((a, b) -> b.compareTo(a));
        System.out.println("Sorted in reverse alphabetical order: " + names);


   
        List<Integer> numbers = Arrays.asList(10, 25, 30, 45, 50, 60);

        System.out.println("\nOriginal numbers: " + numbers);

       
        List<Integer> greaterThan30 = numbers.stream().filter(n -> n > 30).collect(Collectors.toList());

        System.out.println("Numbers greater than 30: " + greaterThan30);
    }
}
