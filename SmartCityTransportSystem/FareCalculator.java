@FunctionalInterface
public interface FareCalculator {
    double calculateFare(Location from, Location to, double baseFare);
}
