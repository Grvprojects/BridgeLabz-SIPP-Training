import java.util.*;
import java.time.*;

public class TaxiService implements TransportService {
    private final String id;
    private final List<Schedule> schedules;
    private final String type = "TAXI";

    public TaxiService(String id, List<Schedule> schedules) {
        this.id = id; this.schedules = schedules;
    }
    @Override public String getId() { return id; }
    @Override public String getType() { return type; }
    @Override public List<Schedule> getSchedules() { return schedules; }
    @Override public double getFareEstimate(Location from, Location to) {
        // taxis are per-distance heavy
        double base = 20.0;
        double dist = GeoUtils.calculateDistance(from, to);
        return base + dist * 10.0;
    }
}
