import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;


enum Status {
    DELIVERED,
    PENDING,
    CANCELLED
}


class Order {
    private int id;
    private String customerName;
    private double amount;
    private Status status;

    public Order(int id, String customerName, double amount, Status status) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
    }

    public String getCustomerName() { return customerName; }
    public double getAmount() { return amount; }
    public Status getStatus() { return status; }
    
    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", customerName='" + customerName + '\'' +
               ", amount=" + amount +
               ", status=" + status +
               '}';
    }
}

public class ECommerceOrders {

    public static void main(String[] args) {
      
        List<Order> orders = List.of(
            new Order(1, "Sparsh", 100.0, Status.DELIVERED),
            new Order(2, "Prateek", 250.0, Status.DELIVERED),
            new Order(3, "Raj", 50.0, Status.PENDING),
            new Order(4, "Shil", 75.0, Status.DELIVERED),
            new Order(5, "Naman", 300.0, Status.CANCELLED),
            new Order(6, "bkaj", 120.0, Status.DELIVERED),
            new Order(7, "Ram", 90.0, Status.DELIVERED),
            new Order(8, "Shyam", 200.0, Status.PENDING),
            new Order(9, "Chetan", 60.0, Status.DELIVERED)
        );

       
        double totalRevenue = orders.stream()
                .filter(order -> order.getStatus() == Status.DELIVERED)
                .mapToDouble(Order::getAmount)
                .sum();
        System.out.printf("1. Total revenue from delivered orders: $%.2f%n%n", totalRevenue);

       
        Map<String, Double> customerTotalSpend = orders.stream()
                .collect(Collectors.groupingBy(
                    Order::getCustomerName,
                    Collectors.summingDouble(Order::getAmount)
                ));
        System.out.println("2. Total spend per customer: " + customerTotalSpend + "\n");
        
     
        String topSpender = customerTotalSpend.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
        System.out.println("3. Customer with the maximum total spend: " + topSpender + "\n");
        
       
        Map<Boolean, List<Order>> partitionedOrders = orders.stream()
                .collect(Collectors.partitioningBy(order -> order.getStatus() == Status.DELIVERED));
        System.out.println("4. Completed (DELIVERED) orders: " + partitionedOrders.get(true));
        System.out.println("   Incomplete (PENDING/CANCELLED) orders: " + partitionedOrders.get(false) + "\n");
        
      
        List<String> uniqueCustomers = orders.stream()
                .map(Order::getCustomerName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("5. Unique customers: " + uniqueCustomers);
    }
}