import java.util.*;
import java.time.*;

public class BusService implements TransportService, GeoUtils {
    private final String id;
    private final List<Schedule> schedules;
    private final String type = "BUS";

    public BusService(String id, List<Schedule> schedules) {
        this.id = id; this.schedules = schedules;
    }
    @Override public String getId() { return id; }
    @Override public String getType() { return type; }
    @Override public List<Schedule> getSchedules() { return schedules; }
    @Override public double getFareEstimate(Location from, Location to) {
        // fare base on distance and average schedule fare
        double avgFare = schedules.stream().mapToDouble(Schedule::getFare).average().orElse(10.0);
        double dist = GeoUtils.calculateDistance(from, to);
        return avgFare + dist * 0.5;
    }
}
