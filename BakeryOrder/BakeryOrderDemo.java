import java.util.*;

// Generic Order class
class Order<T> {
    private String itemName;
    private int quantity;
    private T category;

    public Order(String itemName, int quantity, T category) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public T getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Order: " + itemName + ", Quantity: " + quantity + ", Category: " + category;
    }
}

// Order Management System
class BakeryOrderSystem<T> {
    private Queue<Order<T>> orderQueue;

    public BakeryOrderSystem() {
        orderQueue = new LinkedList<>();
    }

    // Add order to the queue
    public void addOrder(Order<T> order) {
        orderQueue.offer(order);
        System.out.println("Added: " + order);
    }

    // Deliver the next order in FIFO
    public Order<T> deliverOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders to deliver.");
            return null;
        }
        Order<T> delivered = orderQueue.poll();
        System.out.println("Delivered: " + delivered);
        return delivered;
    }

    // Filter orders by category (e.g., cake, cookie, etc.)
    public List<Order<T>> filterOrdersByCategory(T category) {
        List<Order<T>> filtered = new ArrayList<>();
        for (Order<T> order : orderQueue) {
            if (order.getCategory().equals(category)) {
                filtered.add(order);
            }
        }
        return filtered;
    }

    // Show all pending orders
    public void showAllOrders() {
        if (orderQueue.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }
        System.out.println("Pending Orders:");
        for (Order<T> order : orderQueue) {
            System.out.println(order);
        }
    }
}

// Main class to run the system
public class BakeryOrderDemo {
    public static void main(String[] args) {
        BakeryOrderSystem<String> bakerySystem = new BakeryOrderSystem<>();

        // Add some orders
        bakerySystem.addOrder(new Order<>("Chocolate Cake", 2, "Cake"));
        bakerySystem.addOrder(new Order<>("Strawberry Pastry", 3, "Pastry"));
        bakerySystem.addOrder(new Order<>("Oatmeal Cookie", 5, "Cookie"));
        bakerySystem.addOrder(new Order<>("Cheesecake", 1, "Cake"));

        // Show all orders
        bakerySystem.showAllOrders();

        // Deliver one order (FIFO)
        bakerySystem.deliverOrder();

        // Filter by category "Cake"
        System.out.println("\nFiltered Orders - Category: Cake");
        List<Order<String>> cakes = bakerySystem.filterOrdersByCategory("Cake");
        for (Order<String> order : cakes) {
            System.out.println(order);
        }

        // Show remaining orders
        System.out.println("\nRemaining Orders:");
        bakerySystem.showAllOrders();
    }
}
