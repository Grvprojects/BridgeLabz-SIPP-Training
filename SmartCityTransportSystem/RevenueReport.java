import java.util.*;
import java.util.stream.*;

public class RevenueReport {
    public void summarize(List<Trip> trips) {
        System.out.println("--- Revenue Summary (groupingBy, partitioningBy, summarizingDouble) ---");
        // total revenue by service
        Map<String, Double> revenueByService = trips.stream()
            .collect(Collectors.groupingBy(Trip::getServiceId, Collectors.summingDouble(Trip::getFare)));
        System.out.println("Revenue by service: " + revenueByService);

        // partition peak vs non-peak
        Map<Boolean, Double> revenueByPeak = trips.stream()
            .collect(Collectors.partitioningBy(Trip::isPeak, Collectors.summingDouble(Trip::getFare)));
        System.out.println("Revenue peak / non-peak: " + revenueByPeak);

        // summarizing overall fares
        DoubleSummaryStatistics stats = trips.stream()
            .collect(Collectors.summarizingDouble(Trip::getFare));
        System.out.println("Fares stats -> count:" + stats.getCount() + " total:" + stats.getSum() + " avg:" + stats.getAverage() + " max:" + stats.getMax());
    }
}
