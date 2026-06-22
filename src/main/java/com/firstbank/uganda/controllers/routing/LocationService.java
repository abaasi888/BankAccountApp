package com.firstbank.uganda.controllers.routing;
import com.firstbank.uganda.models.customer.Customer;
import java.util.*;
public class LocationService {
    private Map<String, Coordinates> branchLocations;
    public LocationService() {
        this.branchLocations = new HashMap<>();
        branchLocations.put("Kampala", new Coordinates(0.3136, 32.5811));
        branchLocations.put("Gulu", new Coordinates(2.7800, 32.2900));
        branchLocations.put("Mbarara", new Coordinates(-0.6072, 30.6545));
        branchLocations.put("Jinja", new Coordinates(0.4244, 33.2041));
        branchLocations.put("Mbale", new Coordinates(1.0650, 34.1810));
    }
    public List<String> getNearestBranches(Customer customer, int limit) {
        List<String> branches = new ArrayList<>(branchLocations.keySet());
        Collections.sort(branches);
        return branches.subList(0, Math.min(limit, branches.size()));
    }
    public double calculateDistance(String branch1, String branch2) {
        Coordinates c1 = branchLocations.get(branch1);
        Coordinates c2 = branchLocations.get(branch2);
        if (c1 == null || c2 == null) return 0;
        return c1.getDistanceTo(c2);
    }
    public static class Coordinates {
        private final double latitude, longitude;
        public Coordinates(double lat, double lng) { this.latitude = lat; this.longitude = lng; }
        public double getDistanceTo(Coordinates other) {
            double lat1 = Math.toRadians(this.latitude), lon1 = Math.toRadians(this.longitude);
            double lat2 = Math.toRadians(other.latitude), lon2 = Math.toRadians(other.longitude);
            double dlon = lon2 - lon1, dlat = lat2 - lat1;
            double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
            return 6371 * 2 * Math.asin(Math.sqrt(a));
        }
        public double getLatitude() { return latitude; }
        public double getLongitude() { return longitude; }
    }
}
