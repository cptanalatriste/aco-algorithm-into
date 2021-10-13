package aco.intro;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BruteForceTsp {

    private final List<String> cites;
    private final Map<String, Map<String, Integer>> distanceMap;

    public BruteForceTsp(Map<String, Map<String, Integer>> distanceMap) {
        this.distanceMap = distanceMap;
        this.cites = new ArrayList<>(distanceMap.keySet());
    }

    private List<String> findOptimalRoute() {
        List<List<String>> allPermutations = PermutationGenerator.getAllPermutations(this.cites);

        List<String> optimalRoute = List.of();
        int optimalDistance = Integer.MAX_VALUE;

        for (List<String> candidateRoute : allPermutations) {
            int currentRouteDistance = TravellingHelper.calculateDistance(candidateRoute, distanceMap);
            if (currentRouteDistance < optimalDistance) {
                optimalRoute = candidateRoute;
                optimalDistance = currentRouteDistance;
            }
        }

        return optimalRoute;
    }


    public static void main(String[] args) {

//        Map<String, Map<String, Integer>> distanceMap = TravellingHelper.getVerySmallProblem();
        Map<String, Map<String, Integer>> distanceMap = TravellingHelper.getSmallProblem();

        Instant start = Instant.now();
        BruteForceTsp bruteForceTsp = new BruteForceTsp(distanceMap);
        List<String> optimalRoute = bruteForceTsp.findOptimalRoute();
        Instant end = Instant.now();
        System.out.println("Optimal route: " + optimalRoute);
        System.out.println("Optimal distance (miles): " + TravellingHelper.calculateDistance(optimalRoute, distanceMap));
        System.out.println("Execution time (milliSeconds): " + Duration.between(start, end).toMillis());
    }


}
