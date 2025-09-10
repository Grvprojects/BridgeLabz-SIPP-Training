import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

class Item {
    String name;
    Item(String name) { this.name = name; }
}

class Order {
    LocalDate date;
    List<Item> items;
    Order(LocalDate date, List<Item> items) {
        this.date = date;
        this.items = items;
    }
    public LocalDate getDate() { return date; }

    public List<Item> getItems(){return items;}
}

class Customer {
    String name;
    List<Order> orders;
    Customer(String name, List<Order> orders) {
        this.name = name;
        this.orders = orders;
    }
    public List<Order> getOrders() { return orders; }
}

class Store {
    Map<String, Customer> customers;
    Store(Map<String, Customer> customers) {
        this.customers = customers;
    }
    public Map<String, Customer> getCustomers() { return customers; }
}

class City {
    List<Store> stores;
    City(List<Store> stores) { this.stores = stores; }
    public List<Store> getStores() { return stores; }
}

public class SalesDashboard {
    public static void main(String[] args) {
      
        Item i1 = new Item("Laptop");
        Item i2 = new Item("Phone");
        Item i3 = new Item("Headphones");

        Order o1 = new Order(LocalDate.now().minusDays(10), Arrays.asList(i1, i2));
        Order o2 = new Order(LocalDate.now().minusDays(20), Arrays.asList(i3));
        Order o3 = new Order(LocalDate.now().minusDays(50), Arrays.asList(i1));
        Order o4 = new Order(LocalDate.now().minusDays(100), Arrays.asList(i2));

        Customer c1 = new Customer("Sparsh", Arrays.asList(o1, o2, o3, o4));
        Customer c2 = new Customer("Raj", Arrays.asList(o1, o2));

        Store s1 = new Store(Map.of("Sparsh", c1, "Raj", c2));
        City city1 = new City(Arrays.asList(s1));

        List<City> cities = Arrays.asList(city1);

       
        List<Item> recentItems = cities.stream()
            .flatMap(city -> city.getStores().stream())                    
            .flatMap(store -> store.getCustomers().values().stream())    
            .filter(customer -> {                                            
                long count = customer.getOrders().stream()
                        .filter(order -> order.getDate()
                        .isAfter(LocalDate.now().minusDays(60)))
                        .count();
                return count >= 3;
            })
            .flatMap(customer -> customer.getOrders().stream())             
            .filter(order -> order.getDate().isAfter(LocalDate.now().minusDays(60)))
            .flatMap(order -> order.getItems().stream())                    
            .collect(Collectors.toList());

        // --- Output ---
        recentItems.forEach(item -> System.out.println(item.name));
    }
}
