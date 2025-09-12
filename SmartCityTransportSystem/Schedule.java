import java.time.LocalTime;

public class Schedule {
    private final LocalTime departure;
    private final LocalTime arrival;
    private final double fare;
    private final boolean peak;

    public Schedule(LocalTime departure, LocalTime arrival, double fare, boolean peak) {
        this.departure = departure; this.arrival = arrival; this.fare = fare; this.peak = peak;
    }
    public LocalTime getDeparture() { return departure; }
    public LocalTime getArrival() { return arrival; }
    public double getFare() { return fare; }
    public boolean isPeak() { return peak; }

    @Override public String toString() {
        return departure + " -> " + arrival + " fare:" + fare + (peak?" (PEAK)":"");
    }
}
