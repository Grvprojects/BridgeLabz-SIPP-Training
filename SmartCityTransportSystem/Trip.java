import java.time.LocalDateTime;

public class Trip {
    private final String serviceId;
    private final String route;
    private final Passenger passenger;
    private final LocalDateTime timestamp;
    private final double fare;
    private final boolean peak;

    public Trip(String serviceId, String route, Passenger passenger, LocalDateTime timestamp, double fare, boolean peak) {
        this.serviceId = serviceId; this.route = route; this.passenger = passenger; this.timestamp = timestamp; this.fare = fare; this.peak = peak;
    }
    public String getServiceId() { return serviceId; }
    public String getRoute() { return route; }
    public Passenger getPassenger() { return passenger; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public double getFare() { return fare; }
    public boolean isPeak() { return peak; }

    @Override public String toString() {
        return timestamp + ": " + passenger + " on " + serviceId + " route:" + route + " fare:" + fare;
    }
}
