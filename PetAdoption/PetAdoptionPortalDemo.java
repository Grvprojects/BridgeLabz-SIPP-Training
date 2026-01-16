import java.util.*;

// Base interface
interface Animal {
    String getType();
    int getAge();
    boolean isAdopted();
    void adopt();
}

// Generic Pet class
class Pet<T extends Animal> {
    private T animal;

    public Pet(T animal) {
        this.animal = animal;
    }

    public T getAnimal() {
        return animal;
    }
}

// Concrete Dog class
class Dog implements Animal {
    private int age;
    private boolean isAdopted;

    public Dog(int age) {
        this.age = age;
        this.isAdopted = false;
    }

    public String getType() {
        return "Dog";
    }

    public int getAge() {
        return age;
    }

    public boolean isAdopted() {
        return isAdopted;
    }

    public void adopt() {
        isAdopted = true;
    }

    public String toString() {
        return "Dog - Age: " + age + ", Adopted: " + isAdopted;
    }
}

// Concrete Cat class
class Cat implements Animal {
    private int age;
    private boolean isAdopted;

    public Cat(int age) {
        this.age = age;
        this.isAdopted = false;
    }

    public String getType() {
        return "Cat";
    }

    public int getAge() {
        return age;
    }

    public boolean isAdopted() {
        return isAdopted;
    }

    public void adopt() {
        isAdopted = true;
    }

    public String toString() {
        return "Cat - Age: " + age + ", Adopted: " + isAdopted;
    }
}

// Generic Pet Adoption Portal
class PetAdoptionPortal<T extends Animal> {
    private List<Pet<T>> pets;

    public PetAdoptionPortal() {
        pets = new ArrayList<>();
    }

    public void addPet(T pet) {
        pets.add(new Pet<>(pet));
    }

    public List<T> filterAvailablePets(String type, int maxAge) {
        List<T> result = new ArrayList<>();
        for (Pet<T> pet : pets) {
            T animal = pet.getAnimal();
            if (!animal.isAdopted() && animal.getType().equalsIgnoreCase(type) && animal.getAge() <= maxAge) {
                result.add(animal);
            }
        }
        return result;
    }

    public void showAllPets() {
        for (Pet<T> pet : pets) {
            System.out.println(pet.getAnimal());
        }
    }
}

// Main class to run the program
public class PetAdoptionPortalDemo {
    public static void main(String[] args) {
        PetAdoptionPortal<Animal> portal = new PetAdoptionPortal<>();

        // Add pets
        Dog dog1 = new Dog(3);
        Dog dog2 = new Dog(5);
        Cat cat1 = new Cat(2);
        Cat cat2 = new Cat(6);

        portal.addPet(dog1);
        portal.addPet(dog2);
        portal.addPet(cat1);
        portal.addPet(cat2);

        // Adopt one pet
        cat1.adopt();

        // Display all pets
        System.out.println("All Pets:");
        portal.showAllPets();

        // Filter available dogs under age 4
        System.out.println("\nAvailable Dogs under age 4:");
        List<Animal> filteredDogs = portal.filterAvailablePets("Dog", 4);
        for (Animal dog : filteredDogs) {
            System.out.println(dog);
        }
    }
}
