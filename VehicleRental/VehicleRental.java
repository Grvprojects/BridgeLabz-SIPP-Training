import java.util.Scanner; // for user input

interface Rentable {
    double calculateRent(int days); // Abstract method for rent calculation
}

//  Abstract Class - Base for all vehicles
abstract class Vehicle implements Rentable {
    //  Encapsulation + protected access for Inheritance
    protected String model;
    protected String numberPlate;
    protected double baseRate;

    // Constructor for initializing vehicle
    public Vehicle(String model, String numberPlate, double baseRate) {
        this.model = model;
        this.numberPlate = numberPlate;
        this.baseRate = baseRate;
    }

    // Encapsulation (fields accessed via methods)
    public String getModel() {
        return model;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public double getBaseRate() {
        return baseRate;
    }
}

//Inheritance from Vehicle
class Bike extends Vehicle {
    // Constructor
    public Bike(String model, String numberPlate, double baseRate) {
        super(model, numberPlate, baseRate);
    }

    // Polymorphism - Overriding calculateRent() for Bike
    public double calculateRent(int days) {
        return baseRate * days; // Operator
    }
}

// Subclass - Inheritance from Vehicle
class Car extends Vehicle {
    public Car(String model, String numberPlate, double baseRate) {
        super(model, numberPlate, baseRate);
    }

    // Polymorphism - Overriding calculateRent() for Car
    public double calculateRent(int days) {
        double surcharge = 50; // flat fee
        return (baseRate * days) + surcharge; // Operator
    }
}

//Subclass - Inheritance from Vehicle
class Truck extends Vehicle {
    public Truck(String model, String numberPlate, double baseRate) {
        super(model, numberPlate, baseRate);
    }

    // Polymorphism - Overriding calculateRent() for Truck
    public double calculateRent(int days) {
        double heavyLoadFee = 100;
        return (baseRate * days) + heavyLoadFee; // Operator
    }
}

// Class - Represents the Customer
class Customer {
    //  Encapsulation 
    private String name;
    private String contact;

    // Constructor
    public Customer(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    // Method for renting a vehicle
    public void rentVehicle(Vehicle vehicle, int days) {
        System.out.println("\nCustomer: " + name);
        System.out.println("Vehicle Model: " + vehicle.getModel());
        System.out.println("Number Plate: " + vehicle.getNumberPlate());

        // Polymorphism: calculateRent() works 
        System.out.println("Total Rent for " + days + " days: ₹" + vehicle.calculateRent(days));
    }
}

public class VehicleRental {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner for user input

        // Creating customer (Encapsulation via constructor)
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your contact number: ");
        String contact = sc.nextLine();

        Customer customer1 = new Customer(name, contact);

        System.out.println("\nChoose Vehicle Type:");
        System.out.println("1. Bike");
        System.out.println("2. Car");
        System.out.println("3. Truck");
        System.out.print("Enter your choice (1-3): ");
        int choice = sc.nextInt();

        System.out.print("Enter number of days for rent: ");
        int days = sc.nextInt();

        // using constructors
        Vehicle vehicle = null;
        if (choice == 1) {
            vehicle = new Bike("Honda Shine", "UP80 AB1234", 600);
        } else if (choice == 2) {
            vehicle = new Car("Maruti Swift", "DL1C Q1234", 800);
        } else if (choice == 3) {
            vehicle = new Truck("Tata Ace", "MH12 XY5678", 1000);
        } else {
            System.out.println("Invalid vehicle choice!");
            sc.close();
            return;
        }

        // Polymorphism
        customer1.rentVehicle(vehicle, days);

        sc.close(); 
    }
}
