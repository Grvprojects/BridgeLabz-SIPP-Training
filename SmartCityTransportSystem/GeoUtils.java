public interface GeoUtils {
    static double calculateDistance(Location a, Location b) {
        // Very simple Euclidean distance for demo purposes
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }
}
