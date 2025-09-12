import java.util.*;
import java.util.stream.*;

public class Dashboard {
    public void displayServices(List<TransportService> services) {
        System.out.println("--- Live Services (forEach & method reference) ---");
        services.forEach(TransportService::printServiceDetails); // method reference
    }

    public void showTopRoutes(List<Trip> trips) {
        System.out.println("--- Top Routes (groupingBy + stream) ---");
        Map<String, Long> byRoute = trips.stream()
            .collect(Collectors.groupingBy(Trip::getRoute, Collectors.counting()));

        byRoute.entrySet().stream()
            .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
            .limit(5)
            .forEach(e -> System.out.println(e.getKey() + " used " + e.getValue() + " times"));
    }
}
