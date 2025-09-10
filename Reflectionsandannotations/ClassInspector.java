import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ClassInspector {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the fully qualified class name (e.g., java.lang.String): ");
            String className = scanner.nextLine();

            // Get the Class object for the given name
            Class<?> clazz = Class.forName(className);
            System.out.println("\n--- Information for class: " + clazz.getName() + " ---\n");

            // --- Display Constructors ---
            System.out.println("## Constructors:");
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            if (constructors.length == 0) {
                System.out.println("No declared constructors.");
            } else {
                for (Constructor<?> constructor : constructors) {
                    System.out.println("  " + constructor);
                }
            }

            // --- Display Fields ---
            System.out.println("\n## Fields:");
            Field[] fields = clazz.getDeclaredFields();
            if (fields.length == 0) {
                System.out.println("No declared fields.");
            } else {
                for (Field field : fields) {
                    System.out.println("  " + field);
                }
            }
            
            // --- Display Methods ---
            System.out.println("\n## Methods:");
            Method[] methods = clazz.getDeclaredMethods();
            if (methods.length == 0) {
                System.out.println("No declared methods.");
            } else {
                for (Method method : methods) {
                    System.out.println("  " + method);
                }
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Class not found. Please provide a valid fully qualified class name.");
        }
    }
}