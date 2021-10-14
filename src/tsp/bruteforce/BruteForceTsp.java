package tsp.bruteforce;

import tsp.TravellingHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BruteForceTsp {


    public List<String> findOptimalRoute(Map<String, Map<String, Integer>> distanceMap) {

        List<String> cities = new ArrayList<>(distanceMap.keySet());
        List<List<String>> allPermutations =
                PermutationGenerator.getAllPermutations(cities);

        List<String> optimalRoute = List.of();
        int optimalDistance = Integer.MAX_VALUE;

        for (List<String> candidateRoute : allPermutations) {
            int currentRouteDistance =
                    TravellingHelper.calculateDistance(candidateRoute, distanceMap);
            if (currentRouteDistance < optimalDistance) {
                optimalRoute = candidateRoute;
                optimalDistance = currentRouteDistance;
            }
        }

        return optimalRoute;
    }

}
