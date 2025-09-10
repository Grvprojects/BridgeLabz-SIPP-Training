import java.lang.reflect.Field;

// The class with a private field
class Person {
    private int age = 25;

    @Override
    public String toString() {
        return "Person [age=" + age + "]";
    }
}

public class PrivateFieldAccess {

    public static void main(String[] args) {
        try {
            Person person = new Person();
            System.out.println("Initial state: " + person);

            // Get the Class object
            Class<?> personClass = person.getClass();

            // Get the private field 'age'
            Field ageField = personClass.getDeclaredField("age");

            // Make the private field accessible 🔐
            ageField.setAccessible(true);

            // Get the current value of the private field
            int currentAge = (int) ageField.get(person);
            System.out.println("Accessed private age value: " + currentAge);

            // Modify the value of the private field
            ageField.set(person, 30);
            System.out.println("Modified state: " + person);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}