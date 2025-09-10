import java.lang.reflect.Method;

// The class with a private method
class Calculator {
    private int multiply(int a, int b) {
        return a * b;
    }
}

public class PrivateMethodInvocation {

    public static void main(String[] args) {
        try {
            Calculator calc = new Calculator();
            
            // Get the Class object
            Class<?> calcClass = calc.getClass();

            // Get the private method 'multiply' that takes two int parameters
            Method multiplyMethod = calcClass.getDeclaredMethod("multiply", int.class, int.class);

            // Make the private method accessible 🚀
            multiplyMethod.setAccessible(true);

            // Invoke the method on the 'calc' instance with arguments 5 and 10
            int result = (int) multiplyMethod.invoke(calc, 5, 10);

            System.out.println("Result of invoking private method multiply(5, 10): " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}