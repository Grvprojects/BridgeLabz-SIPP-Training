import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

// --- 1. Define the custom annotation ---

/**
 * Marks a method as important.
 * The 'level' element indicates the priority.
 */
@Retention(RetentionPolicy.RUNTIME) // Makes the annotation available at runtime via reflection
@Target(ElementType.METHOD)         // Specifies that this annotation can only be applied to methods
@interface ImportantMethod {
    String level() default "HIGH"; // An element with a default value
}

// --- 2. Apply the annotation to methods ---

class MyService {
    @ImportantMethod(level = "CRITICAL")
    public void performCriticalTask() {
        System.out.println("Executing a critical task...");
    }

    @ImportantMethod // Uses the default level "HIGH"
    public void performHighPriorityTask() {
        System.out.println("Executing a high-priority task...");
    }

    public void performRegularTask() {
        System.out.println("Executing a regular, un-annotated task.");
    }
}

// --- 3. Retrieve and print annotated methods using Reflection ---

public class ImportantMethodProcessor {
    public static void main(String[] args) {
        System.out.println("🔍 Searching for important methods in MyService class...\n");

        Class<MyService> serviceClass = MyService.class;

        // Iterate through all declared methods of the class
        for (Method method : serviceClass.getDeclaredMethods()) {
            // Check if the method is annotated with @ImportantMethod
            if (method.isAnnotationPresent(ImportantMethod.class)) {
                // Get the annotation instance from the method
                ImportantMethod annotation = method.getAnnotation(ImportantMethod.class);
                String level = annotation.level();

                System.out.println("✅ Found Important Method: " + method.getName());
                System.out.println("   └── Priority Level: " + level + "\n");
            }
        }
    }
}