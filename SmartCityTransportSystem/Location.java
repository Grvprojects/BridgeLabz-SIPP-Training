public class Location {
    private final double x, y;
    private final String name;

    public Location(String name, double x, double y) {
        this.name = name;
        this.x = x; this.y = y;
    }
    public double getX() { return x; }
    public double getY() { return y; }
    public String getName() { return name; }
    @Override public String toString() { return name + "(" + x + "," + y + ")"; }
}
