import java.lang.reflect.Constructor;

// The class to be instantiated
class Student {
    private String name;
    private int studentId;

    public Student() {
        this.name = "Unknown";
        this.studentId = -1;
    }
    
    public Student(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", studentId=" + studentId + "]";
    }
}

public class DynamicObjectCreation {

    public static void main(String[] args) {
        try {
            // Get the Class object for Student
            Class<?> studentClass = Class.forName("Student");

            // --- 1. Create instance using the no-argument constructor ---
            // Get the constructor with no parameters
            Constructor<?> noArgConstructor = studentClass.getConstructor(); 
            // Create a new instance
            Student student1 = (Student) noArgConstructor.newInstance();
            System.out.println("Created using no-arg constructor: " + student1);


            // --- 2. Create instance using the parameterized constructor ---
            // Get the constructor that takes a String and an int
            Constructor<?> parameterizedConstructor = studentClass.getConstructor(String.class, int.class);
            // Create a new instance with arguments
            Student student2 = (Student) parameterizedConstructor.newInstance("Alice", 12345);
            System.out.println("Created using parameterized constructor: " + student2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}