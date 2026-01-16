import java.util.List;

public interface TransportService {
    String getId();
    String getType();
    List<Schedule> getSchedules();
    double getFareEstimate(Location from, Location to);

    default void printServiceDetails() {
        System.out.println("Service -> id:" + getId() + ", type:" + getType());
        System.out.println("Schedules:");
        getSchedules().forEach(s -> System.out.println("  " + s));
    }
}
