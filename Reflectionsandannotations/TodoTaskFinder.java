import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

// --- 1. Define the custom annotation ---

/**
 * Marks a feature or method as a pending task.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

// --- 2. Apply the annotation to methods ---

class FeatureManager {
    @Todo(task = "Implement user authentication", assignedTo = "Alice")
    public void loginFeature() {
        // Future implementation
    }

    @Todo(task = "Refactor the payment gateway", assignedTo = "Bob", priority = "HIGH")
    public void paymentFeature() {
        // Future implementation
    }

    @Todo(task = "Add logging for user activities", assignedTo = "Charlie")
    public void loggingFeature() {
        // Future implementation
    }
    
    public void completedFeature() {
        // This method is complete and has no @Todo annotation.
    }
}

// --- 3. Retrieve and print all pending tasks using Reflection ---

public class TodoTaskFinder {
    public static void main(String[] args) {
        System.out.println("📋 Scanning FeatureManager for pending tasks...\n" + "----------------------------------------");

        Class<FeatureManager> managerClass = FeatureManager.class;

        // Iterate through all declared methods
        for (Method method : managerClass.getDeclaredMethods()) {
            // Check if the @Todo annotation is present
            if (method.isAnnotationPresent(Todo.class)) {
                // Get the annotation instance
                Todo todo = method.getAnnotation(Todo.class);

                System.out.println("Task for method: " + method.getName());
                System.out.println("  - Description: " + todo.task());
                System.out.println("  - Assigned to: " + todo.assignedTo());
                System.out.println("  - Priority   : " + todo.priority());
                System.out.println("----------------------------------------");
            }
        }
    }
}