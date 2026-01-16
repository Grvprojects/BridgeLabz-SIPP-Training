import java.util.*;
import java.time.*;

public class MetroService implements TransportService {
    private final String id;
    private final List<Schedule> schedules;
    private final String type = "METRO";

    public MetroService(String id, List<Schedule> schedules) {
        this.id = id; this.schedules = schedules;
    }
    @Override public String getId() { return id; }
    @Override public String getType() { return type; }
    @Override public List<Schedule> getSchedules() { return schedules; }
    @Override public double getFareEstimate(Location from, Location to) {
        double avgFare = schedules.stream().mapToDouble(Schedule::getFare).average().orElse(15.0);
        double dist = GeoUtils.calculateDistance(from, to);
        return avgFare + dist * 0.3;
    }
}
