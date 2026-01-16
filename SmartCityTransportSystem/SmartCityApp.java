import java.util.*;
import java.time.*;

public class SmartCityApp {
    public static void main(String[] args) {
        // Create sample locations
        Location downtown = new Location("Downtown", 0, 0);
        Location uptown = new Location("Uptown", 3, 4); // distance 5 from downtown
        Location station = new Location("Station", 1, 1);

        // Create schedules
        Schedule s1 = new Schedule(LocalTime.of(8,0), LocalTime.of(8,30), 20.0, true);
        Schedule s2 = new Schedule(LocalTime.of(9,0), LocalTime.of(9,30), 15.0, false);
        Schedule s3 = new Schedule(LocalTime.of(8,15), LocalTime.of(8,45), 18.0, true);

        BusService bus1 = new BusService("BUS-100", Arrays.asList(s1,s2));
        MetroService metro1 = new MetroService("METRO-A", Arrays.asList(s3));
        TaxiService taxi1 = new TaxiService("TAXI-77", Arrays.asList(s2));
        AmbulanceService amb = new AmbulanceService("AMB-01", Arrays.asList(s1));

        List<TransportService> services = Arrays.asList(bus1, metro1, taxi1, amb);

        // Functional interface usage: FareCalculator with a lambda
        FareCalculator calc = (from, to, base) -> base + GeoUtils.calculateDistance(from,to) * 2.0;

        System.out.println("Fare estimates from Downtown->Uptown:"); 
        services.forEach(s -> System.out.println(s.getId() + ": " + String.format("%.2f", calc.calculateFare(downtown, uptown, 10.0))));

        // Simulate passenger bookings -> lambda filters and sorting (earliest departure)
        List<Passenger> passengers = Arrays.asList(new Passenger("P1","Alice"), new Passenger("P2","Bob"), new Passenger("P3","Charlie"));

        // Filter services that have a schedule departing before 8:30, sort by lowest average fare using lambdas
        List<TransportService> filtered = services.stream()
            .filter(s -> s.getSchedules().stream().anyMatch(sc -> sc.getDeparture().isBefore(LocalTime.of(8,30))))
            .sorted((a,b) -> Double.compare(
                a.getSchedules().stream().mapToDouble(Schedule::getFare).average().orElse(Double.MAX_VALUE),
                b.getSchedules().stream().mapToDouble(Schedule::getFare).average().orElse(Double.MAX_VALUE)
            ))
            .collect(Collectors.toList());

        System.out.println("\nFiltered & sorted services (depart before 8:30):");
        filtered.forEach(TransportService::printServiceDetails);

        // Create trips - simulating bookings and usage
        List<Trip> trips = new ArrayList<>();
        trips.add(new Trip(bus1.getId(), "D-U", passengers.get(0), LocalDateTime.now(), bus1.getFareEstimate(downtown, uptown), true));
        trips.add(new Trip(metro1.getId(), "D-S", passengers.get(1), LocalDateTime.now().minusMinutes(10), metro1.getFareEstimate(downtown, station), false));
        trips.add(new Trip(taxi1.getId(), "D-U", passengers.get(2), LocalDateTime.now().minusMinutes(5), taxi1.getFareEstimate(downtown, uptown), true));
        trips.add(new Trip(amb.getId(), "EMER", new Passenger("P4","Emergency Patient"), LocalDateTime.now(), 0.0, true));

        // Dashboard updates live using forEach
        Dashboard dashboard = new Dashboard();
        System.out.println("\nDashboard live services:"); 
        dashboard.displayServices(services);

        // Show top routes
        dashboard.showTopRoutes(trips);

        // Revenue Reports
        RevenueReport report = new RevenueReport();
        report.summarize(trips);

        // Group passengers by route using collectors
        System.out.println("\nPassengers grouped by route:"); 
        Map<String, List<Passenger>> byRoute = trips.stream().collect(Collectors.groupingBy(Trip::getRoute, Collectors.mapping(Trip::getPassenger, Collectors.toList())));
        byRoute.forEach((r,plist) -> System.out.println(r + " -> " + plist));

        // Partition trips into peak vs non-peak
        Map<Boolean, List<Trip>> peakPartition = trips.stream().collect(Collectors.partitioningBy(Trip::isPeak));
        System.out.println("\nPeak partition sizes: " + peakPartition.get(true).size() + " / " + peakPartition.get(false).size());

        // Detect emergency services (marker interface)
        System.out.println("\nEmergency-capable services:"); 
        services.stream()
            .filter(s -> s instanceof EmergencyService)
            .forEach(s -> System.out.println(" PRIORITY -> " + s.getId()));

        System.out.println("\nDemo complete - system can be extended by adding new classes implementing TransportService (for example FerryService or BikeRental)."); 
    }
}
