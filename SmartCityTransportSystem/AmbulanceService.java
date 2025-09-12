import java.util.*;
import java.time.*;

/**
 Ambulance is a transport that also marks EmergencyService so it can be prioritized.
*/
public class AmbulanceService implements TransportService, EmergencyService {
    private final String id;
    private final List<Schedule> schedules;
    private final String type = "AMBULANCE";

    public AmbulanceService(String id, List<Schedule> schedules) {
        this.id = id; this.schedules = schedules;
    }
    @Override public String getId() { return id; }
    @Override public String getType() { return type; }
    @Override public List<Schedule> getSchedules() { return schedules; }
    @Override public double getFareEstimate(Location from, Location to) {
        return 0.0; // not fare-driven
    }
}
