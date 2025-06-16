package Core_Java.Day1.Level1;

public class VolumeOfEarth {
    public static double conversion(int radius){
        return radius*1.6;
    }
    public static void main(String[] args) {
        int radius = 6378;
        double radius_in_miles = conversion(radius);
        double volume = ((double) 4 /3)*Math.PI*Math.pow(radius, 3);
        double volume_miles = ((double) 4 /3)*Math.PI*Math.pow(radius_in_miles, 3);
        System.out.println("Volume of Earth in cubic KM is " + volume + " and volume in miles is " + volume_miles);
    }
}
