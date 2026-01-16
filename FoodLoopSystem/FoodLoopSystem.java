import java.util.*;

// Interface: IOrderable
interface IOrderable {
    void placeOrder();
    void cancelOrder();
}

// Encapsulation: Stock levels hidden via private variable
abstract class FoodItem {
    private String name;
    private String category;
    private double price;
    private boolean availability;
    private int stock;

    public FoodItem(String name, String category, double price, boolean availability, int stock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.availability = availability;
        this.stock = stock;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return availability && stock > 0; }
    public int getStock() { return stock; }
    public void reduceStock() { if (stock > 0) stock--; }

    public double applyDiscount(double total) {
        return total; // Default: no discount
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - ₹" + price + (isAvailable() ? " [Available]" : " [Out of Stock]");
    }
}

// Inheritance: VegItem extends FoodItem
class VegItem extends FoodItem {
    public VegItem(String name, double price, int stock) {
        super(name, "Veg", price, true, stock);
    }
}

// Inheritance: NonVegItem extends FoodItem
class NonVegItem extends FoodItem {
    public NonVegItem(String name, double price, int stock) {
        super(name, "Non-Veg", price, true, stock);
    }
}

// Class: Order with operator-like logic (calculate total - discounts)
class Order implements IOrderable {
    private List<FoodItem> itemList = new ArrayList<>();
    private double totalAmount = 0;

    public void addItem(FoodItem item) {
        if (item.isAvailable()) {
            itemList.add(item);
            totalAmount += item.getPrice();
            item.reduceStock();
        } else {
            System.out.println(item.getName() + " is out of stock.");
        }
    }

    public double applyDiscount() {
        if (totalAmount > 500) {
            return totalAmount * 0.9; // 10% discount
        } else if (totalAmount > 200) {
            return totalAmount * 0.95; // 5% discount
        }
        return totalAmount;
    }

    @Override
    public void placeOrder() {
        System.out.println("\n--------- BILL RECEIPT ---------");
        for (FoodItem item : itemList) {
            System.out.printf("- %-20s ₹%.2f\n", item.getName(), item.getPrice());
        }
        System.out.println("--------------------------------");
        System.out.printf("Subtotal:              ₹%.2f\n", totalAmount);
        System.out.printf("Total after Discount:  ₹%.2f\n", applyDiscount());
        System.out.println("--------------------------------");
        System.out.println("Order placed successfully!");
        System.out.println("--------------------------------");
    }

    @Override
    public void cancelOrder() {
        System.out.println("Order canceled.");
        itemList.clear();
        totalAmount = 0;
    }
}

// Constructor Use Case: ComboMeal as a combination of food items
class ComboMeal extends Order {
    public ComboMeal(FoodItem... items) {
        for (FoodItem item : items) {
            addItem(item);
        }
        System.out.println("Combo meal created!");
    }
}

// Main class with user input
public class FoodLoopSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Menu Items
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new VegItem("Paneer Tikka", 150, 10));
        menu.add(new NonVegItem("Butter Chicken", 250, 5));
        menu.add(new VegItem("Veg Biryani", 120, 8));
        menu.add(new NonVegItem("Chicken Lollipop", 180, 6));
        menu.add(new VegItem("Chole Bhature", 100, 10));

        Order userOrder = new Order();

        while (true) {
            System.out.println("\n------ FoodLoop Menu ------");
            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i + 1) + ". " + menu.get(i));
            }
            System.out.println("0. Place Order");
            System.out.print("Choose item number to add to cart (or 0 to finish): ");
            int choice = sc.nextInt();

            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= menu.size()) {
                userOrder.addItem(menu.get(choice - 1));
                System.out.println(menu.get(choice - 1).getName() + " added to your cart.");
            } else {
                System.out.println("Invalid choice!");
            }
        }

        if (!confirm("Do you want to place the order? (y/n): ", sc)) {
            userOrder.cancelOrder();
        } else {
            userOrder.placeOrder();
        }

        sc.close();
    }

    public static boolean confirm(String msg, Scanner sc) {
        System.out.print(msg);
        String ans = sc.next().toLowerCase();
        return ans.equals("y") || ans.equals("yes");
    }
}
