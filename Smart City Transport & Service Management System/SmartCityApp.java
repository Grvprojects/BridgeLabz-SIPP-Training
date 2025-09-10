// SmartCityApp.java
// Java 8 demo implementation for a Smart City Transport & Service Management System
// Showcasing: Lambdas, Method References, Default & Static Interface Methods, Streams, Collectors,
// Functional Interfaces, Marker Interfaces, and clean extensible design.

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;
import java.util.stream.*;

public class SmartCityApp {


    enum TransportType { BUS, METRO, TAXI, FERRY, AMBULANCE, FIRE }

    static class Location {
        final double lat; // degrees
        final double lon; // degrees
        Location(double lat, double lon) { this.lat = lat; this.lon = lon; }
        @Override public String toString() { return String.format("(%.4f, %.4f)", lat, lon); }
    }

    static class Route {
        final String id;
        final String from;
        final String to;
        final Location fromLoc;
        final Location toLoc;
        Route(String id, String from, String to, Location fromLoc, Location toLoc) {
            this.id = id; this.from = from; this.to = to; this.fromLoc = fromLoc; this.toLoc = toLoc;
        }
        public double distanceKm() { // uses static interface method
            return GeoUtils.calculateDistance(fromLoc, toLoc);
        }
        @Override public String toString() { return id + " (" + from + " → " + to + ")"; }
    }

    static class ScheduleEntry {
        final String routeId;
        final LocalDateTime departure;
        final LocalDateTime arrival;
        final double baseFare; // suggested base fare for the leg
        ScheduleEntry(String routeId, LocalDateTime departure, LocalDateTime arrival, double baseFare) {
            this.routeId = routeId; this.departure = departure; this.arrival = arrival; this.baseFare = baseFare;
        }
        @Override public String toString() {
            return String.format("[%s] %s → %s | Base Fare: %.2f",
                    routeId,
                    departure.format(DateTimeFormatter.ofPattern("HH:mm")),
                    arrival.format(DateTimeFormatter.ofPattern("HH:mm")),
                    baseFare);
        }
    }

    static class Passenger {
        final String id;
        final String name;
        Passenger(String id, String name) { this.id = id; this.name = name; }
        @Override public String toString() { return name + "(" + id + ")"; }
    }

    static class Trip {
        final String tripId;
        final Passenger passenger;
        final String serviceId;
        final TransportType transportType;
        final String routeId;
        final LocalDateTime startTime;
        final LocalDateTime endTime;
        final double fareCharged;
        Trip(String tripId, Passenger passenger, String serviceId, TransportType transportType,
             String routeId, LocalDateTime startTime, LocalDateTime endTime, double fareCharged) {
            this.tripId = tripId; this.passenger = passenger; this.serviceId = serviceId; this.transportType = transportType;
            this.routeId = routeId; this.startTime = startTime; this.endTime = endTime; this.fareCharged = fareCharged;
        }
        boolean isPeak() { return isPeakHour(startTime); }
        static boolean isPeakHour(LocalDateTime time) {
            int h = time.getHour();
            return (h >= 8 && h < 10) || (h >= 17 && h < 20);
        }
    }

   
    interface GeoUtils {
        // Haversine distance (km)
        static double calculateDistance(Location a, Location b) {
            double R = 6371.0; // km
            double dLat = Math.toRadians(b.lat - a.lat);
            double dLon = Math.toRadians(b.lon - a.lon);
            double lat1 = Math.toRadians(a.lat);
            double lat2 = Math.toRadians(b.lat);
            double sinDLat = Math.sin(dLat/2), sinDLon = Math.sin(dLon/2);
            double h = sinDLat*sinDLat + Math.cos(lat1)*Math.cos(lat2)*sinDLon*sinDLon;
            return 2 * R * Math.asin(Math.sqrt(h));
        }
    }

 
    @FunctionalInterface
    interface FareCalculator {
        double calculateFare(double baseFare, double distanceKm, boolean peak);
       
        default double surge(double base, boolean peak) { return peak ? base * 1.25 : base; }
    }

 
    interface EmergencyService { /* marker: no methods */ }

  
    interface TransportService {
        String getId();
        TransportType getType();
        List<ScheduleEntry> getSchedule();
        Optional<Location> getCurrentLocation();
        Optional<ScheduleEntry> nextDepartureFor(String routeId, LocalDateTime after);
        FareCalculator getFareCalculator();

        // Default method accessible to all services
        default void printServiceDetails() {
            System.out.println("Service: " + getId() + " [" + getType() + "]");
            getCurrentLocation().ifPresent(loc -> System.out.println("  Location: " + loc));
            System.out.println("  Upcoming Schedules:");
            getSchedule().stream()
                    .sorted(Comparator.comparing(se -> se.departure))
                    .limit(3)
                    .forEach(System.out::println); // 2. Method reference demo
        }

        default boolean isActiveAt(LocalDateTime time) {
            return getSchedule().stream().anyMatch(se -> !time.isAfter(se.arrival));
        }

        default boolean servesRoute(String routeId) {
            return getSchedule().stream().anyMatch(se -> se.routeId.equals(routeId));
        }

        // Compute fare for a hypothetical leg on this service
        default double quoteFare(String routeId, LocalDateTime start, double distanceKm) {
            Optional<ScheduleEntry> slot = nextDepartureFor(routeId, start);
            if (!slot.isPresent()) return Double.POSITIVE_INFINITY; // not available
            boolean peak = Trip.isPeakHour(start);
            double base = slot.get().baseFare;
            return getFareCalculator().calculateFare(base, distanceKm, peak);
        }
    }


    static abstract class BaseService implements TransportService {
        final String id;
        final TransportType type;
        final List<ScheduleEntry> schedule = new ArrayList<>();
        Optional<Location> currentLocation = Optional.empty();
        final FareCalculator fareCalculator;
        BaseService(String id, TransportType type, FareCalculator fareCalculator) {
            this.id = id; this.type = type; this.fareCalculator = fareCalculator;
        }
        public String getId() { return id; }
        public TransportType getType() { return type; }
        public List<ScheduleEntry> getSchedule() { return schedule; }
        public Optional<Location> getCurrentLocation() { return currentLocation; }
        public FareCalculator getFareCalculator() { return fareCalculator; }
        public void setCurrentLocation(Location loc) { this.currentLocation = Optional.ofNullable(loc); }
        public void addSchedule(ScheduleEntry s) { schedule.add(s); }
        public Optional<ScheduleEntry> nextDepartureFor(String routeId, LocalDateTime after) {
            return schedule.stream()
                    .filter(se -> se.routeId.equals(routeId) && !se.departure.isBefore(after))
                    .min(Comparator.comparing(se -> se.departure));
        }
    }

    static class BusService extends BaseService {
        BusService(String id, FareCalculator calc) { super(id, TransportType.BUS, calc); }
    }
    static class MetroService extends BaseService {
        MetroService(String id, FareCalculator calc) { super(id, TransportType.METRO, calc); }
    }
    static class TaxiService extends BaseService {
        TaxiService(String id, FareCalculator calc) { super(id, TransportType.TAXI, calc); }
    }
    // 4. System expansion – new FerryService simply implements the interface via BaseService
    static class FerryService extends BaseService {
        FerryService(String id, FareCalculator calc) { super(id, TransportType.FERRY, calc); }
    }

    // Emergency services (marker interface)
    static class AmbulanceService extends BaseService implements EmergencyService {
        AmbulanceService(String id) {
            super(id, TransportType.AMBULANCE, (base, dist, peak) -> base); // flat, no surge
        }
    }

 
    static class DemoData {
        final Map<String, Route> routes = new LinkedHashMap<>();
        final List<Passenger> passengers = new ArrayList<>();
        final List<Trip> tripHistory = new ArrayList<>();

        DemoData() {
            // Define some routes
            routes.put("R1", new Route("R1", "Central", "TechPark",
                    new Location(26.9124, 75.7873), new Location(26.8546, 75.8050)));
            routes.put("R2", new Route("R2", "Airport", "Central",
                    new Location(26.8242, 75.8122), new Location(26.9124, 75.7873)));
            routes.put("R3", new Route("R3", "OldTown", "University",
                    new Location(26.9157, 75.7430), new Location(26.8648, 75.8110)));

            // Passengers
            passengers.add(new Passenger("P1", "Aarav"));
            passengers.add(new Passenger("P2", "Isha"));
            passengers.add(new Passenger("P3", "Dev"));
        }

        Map<String, TransportService> buildServices() {
            Map<String, TransportService> map = new LinkedHashMap<>();

            // 1. Lambda Expressions – fare calculators
            FareCalculator busFare = (base, dist, peak) -> {
                double perKm = 1.0; // ₹ per km
                double fare = base + dist * perKm;
                return peak ? fare * 1.10 : fare; // 10% peak
            };
            FareCalculator metroFare = (base, dist, peak) -> (peak ? base * 1.15 : base) + Math.max(0, dist - 5) * 0.8;
            FareCalculator taxiFare = (base, dist, peak) -> {
                double fare = base + dist * 8.0; // ₹ per km
                return peak ? fare * 1.25 : fare; // surge
            };
            FareCalculator ferryFare = (base, dist, peak) -> base + dist * 2.0;

            BusService b1 = new BusService("BUS-101", busFare);
            MetroService m1 = new MetroService("METRO-A1", metroFare);
            TaxiService t1 = new TaxiService("TAXI-9Z", taxiFare);
            FerryService f1 = new FerryService("FERRY-11", ferryFare);
            AmbulanceService amb = new AmbulanceService("AMB-108");

            // Set locations
            b1.setCurrentLocation(new Location(26.9100, 75.7900));
            m1.setCurrentLocation(new Location(26.9000, 75.8000));
            t1.setCurrentLocation(new Location(26.9050, 75.7950));
            f1.setCurrentLocation(new Location(26.8800, 75.8200));
            amb.setCurrentLocation(new Location(26.9120, 75.7800));

            LocalDateTime now = LocalDateTime.now();
            // Random helper for spacing times
            Function<Integer, LocalDateTime> plusMin = mins -> now.plusMinutes(mins);

            // Schedules
            b1.addSchedule(new ScheduleEntry("R1", plusMin.apply(10), plusMin.apply(45), 10));
            b1.addSchedule(new ScheduleEntry("R2", plusMin.apply(30), plusMin.apply(70), 12));
            b1.addSchedule(new ScheduleEntry("R3", plusMin.apply(55), plusMin.apply(95), 15));

            m1.addSchedule(new ScheduleEntry("R1", plusMin.apply(5), plusMin.apply(25), 25));
            m1.addSchedule(new ScheduleEntry("R2", plusMin.apply(20), plusMin.apply(40), 30));

            t1.addSchedule(new ScheduleEntry("R1", plusMin.apply(3), plusMin.apply(20), 40));
            t1.addSchedule(new ScheduleEntry("R3", plusMin.apply(25), plusMin.apply(50), 50));

            f1.addSchedule(new ScheduleEntry("R2", plusMin.apply(15), plusMin.apply(50), 35));

            amb.addSchedule(new ScheduleEntry("R2", plusMin.apply(0), plusMin.apply(20), 0)); // immediate

            map.put(b1.getId(), b1);
            map.put(m1.getId(), m1);
            map.put(t1.getId(), t1);
            map.put(f1.getId(), f1);
            map.put(amb.getId(), amb);
            return map;
        }
    }


    static class Dashboard {
        // 6. forEach() – live schedule display
        static void showActiveServices(Collection<TransportService> services, LocalDateTime at) {
            System.out.println("\n=== LIVE DASHBOARD @ " + at + " ===");
            services.stream()
                    .filter(s -> s.isActiveAt(at)) // Lambda filter
                    .sorted(Comparator.comparing(TransportService::getId)) // Method reference
                    .forEach(s -> {
                        System.out.println("- " + s.getId() + " [" + s.getType() + "]");
                        s.getSchedule().stream()
                                .filter(se -> !se.departure.isBefore(at))
                                .limit(1)
                                .forEach(se -> System.out.println("  next: " + se));
                    });
        }

        static void highlightEmergenciesFirst(Collection<TransportService> services) {
            System.out.println("\n=== PRIORITY: EMERGENCY SERVICES ===");
            List<TransportService> emergency = services.stream()
                    .filter(s -> s instanceof EmergencyService)
                    .collect(Collectors.toList());
            if (emergency.isEmpty()) {
                System.out.println("No emergency services active.");
            } else {
                emergency.forEach(TransportService::printServiceDetails);
            }
        }
    }

    static class Reports {
        // 7. Collectors – groupingBy, partitioningBy, summarizingDouble
        static void revenueReports(List<Trip> trips) {
            System.out.println("\n=== REVENUE REPORTS ===");

            Map<String, DoubleSummaryStatistics> revenueByRoute = trips.stream()
                    .collect(Collectors.groupingBy(t -> t.routeId,
                            Collectors.summarizingDouble(t -> t.fareCharged)));

            revenueByRoute.forEach((route, stats) -> {
                System.out.printf("Route %s -> trips=%d, total=%.2f, avg=%.2f, min=%.2f, max=%.2f%n",
                        route, stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax());
            });

            Map<Boolean, List<Trip>> peakVsNonPeak = trips.stream()
                    .collect(Collectors.partitioningBy(Trip::isPeak));

            System.out.println("\nPeak vs Non-Peak Split:");
            System.out.println("  Peak trips: " + peakVsNonPeak.get(true).size());
            System.out.println("  Non-peak trips: " + peakVsNonPeak.get(false).size());

            // Top used routes (by count)
            System.out.println("\nTop Routes:");
            trips.stream()
                    .collect(Collectors.groupingBy(t -> t.routeId, Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(3)
                    .forEach(e -> System.out.println("  " + e.getKey() + " -> " + e.getValue() + " trips"));
        }
    }


    static class BookingEngine {
        final Map<String, Route> routes;
        final Map<String, TransportService> services;
        final List<Trip> tripHistory;
        BookingEngine(Map<String, Route> routes, Map<String, TransportService> services, List<Trip> history) {
            this.routes = routes; this.services = services; this.tripHistory = history;
        }

        // 1. Lambda filters and sorts available services (earliest departure, then lowest fare)
        List<TransportService> findBestOptions(String routeId, LocalDateTime desiredStart) {
            Route r = routes.get(routeId);
            double distanceKm = r.distanceKm();
            return services.values().stream()
                    .filter(s -> s.servesRoute(routeId))
                    .map(s -> new AbstractMap.SimpleEntry<>(s, s.nextDepartureFor(routeId, desiredStart)))
                    .filter(e -> e.getValue().isPresent())
                    .sorted(Comparator
                            .comparing((Map.Entry<TransportService, Optional<ScheduleEntry>> e) -> e.getValue().get().departure)
                            .thenComparing(e -> e.getKey().quoteFare(routeId, desiredStart, distanceKm)))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }

        Trip book(Passenger p, TransportService s, String routeId, LocalDateTime start) {
            Route r = routes.get(routeId);
            double distanceKm = r.distanceKm();
            double fare = s.quoteFare(routeId, start, distanceKm);
            LocalDateTime end = start.plusMinutes((long)Math.max(10, distanceKm * 2));
            String tripId = "TRIP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            Trip t = new Trip(tripId, p, s.getId(), s.getType(), routeId, start, end, fare);
            tripHistory.add(t);
            return t;
        }
    }

 

    public static void main(String[] args) {
        DemoData data = new DemoData();
        Map<String, TransportService> services = data.buildServices();
        BookingEngine engine = new BookingEngine(data.routes, services, data.tripHistory);

        // 2. Dashboard updates live
        Dashboard.showActiveServices(services.values(), LocalDateTime.now());
        Dashboard.highlightEmergenciesFirst(services.values());

        // 1. Passenger books a trip – filter & sort by earliest departure, then lowest fare
        Passenger customer = data.passengers.get(0);
        String desiredRoute = "R1"; // Central → TechPark
        LocalDateTime desiredStart = LocalDateTime.now().plusMinutes(2);

        System.out.println("\n=== SEARCH OPTIONS for " + customer + " on route " + desiredRoute + " at " + desiredStart + " ===");
        List<TransportService> options = engine.findBestOptions(desiredRoute, desiredStart);
        options.forEach(s -> {
            double distance = data.routes.get(desiredRoute).distanceKm();
            double quote = s.quoteFare(desiredRoute, desiredStart, distance);
            Optional<ScheduleEntry> slot = s.nextDepartureFor(desiredRoute, desiredStart);
            System.out.printf("%s | depart %s | quote %.2f%n", s.getId(),
                    slot.map(se -> se.departure.format(DateTimeFormatter.ofPattern("HH:mm"))).orElse("-"), quote);
        });

        // Book the first option if available
        if (!options.isEmpty()) {
            TransportService chosen = options.get(0);
            Trip trip = engine.book(customer, chosen, desiredRoute, desiredStart);
            System.out.printf("\nBooked: %s via %s [%s], fare=%.2f, start=%s, end=%s%n",
                    trip.tripId, trip.serviceId, trip.transportType, trip.fareCharged,
                    trip.startTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                    trip.endTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        }

        // Simulate some more historical trips for reports
        seedRandomTrips(data, services);

        // 3. Revenue reports – grouping & summarization
        Reports.revenueReports(data.tripHistory);

        // 4. Extensibility demo – add a new service at runtime
        TransportService ferry = services.values().stream().filter(s -> s.getType() == TransportType.FERRY).findFirst().orElse(null);
        if (ferry != null) {
            System.out.println("\nExtensibility Check: New service detected -> " + ferry.getId());
            ferry.printServiceDetails(); // default method reused
        }

        // 5. Emergency scenario – prioritized
        System.out.println("\nEmergency routing check: any EmergencyService?");
        services.values().stream().filter(s -> s instanceof EmergencyService)
                .findFirst()
                .ifPresent(s -> System.out.println("Immediate priority granted to: " + s.getId()));
    }

    private static void seedRandomTrips(DemoData data, Map<String, TransportService> services) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        List<String> routeIds = new ArrayList<>(data.routes.keySet());
        List<Passenger> pax = data.passengers;
        LocalDateTime base = LocalDateTime.now().minusDays(3);

        for (int i = 0; i < 50; i++) {
            String routeId = routeIds.get(rnd.nextInt(routeIds.size()));
            Passenger p = pax.get(rnd.nextInt(pax.size()));
            TransportService s = services.values().stream()
                    .filter(ts -> ts.servesRoute(routeId))
                    .skip(rnd.nextInt(2))
                    .findFirst().orElse(services.values().iterator().next());
            LocalDateTime start = base.plusHours(rnd.nextInt(72)).withMinute(Arrays.asList(0, 5, 10, 15, 30, 45).get(rnd.nextInt(6)));
            double dist = data.routes.get(routeId).distanceKm();
            double fare = s.quoteFare(routeId, start, dist);
            LocalDateTime end = start.plusMinutes((long)Math.max(10, dist * 2));
            data.tripHistory.add(new Trip("HIST-" + i, p, s.getId(), s.getType(), routeId, start, end, fare));
        }
    }
}
