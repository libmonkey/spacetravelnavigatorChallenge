package system;

import java.util.*;
import java.util.logging.Logger;

public class Universe {

    private List<StarSystem> starSystems;

    public Universe() {
        this.starSystems = new ArrayList<>();
    }

    public boolean addStarSystem(StarSystem system) {
        if(getStarSystem(system.getShortcut()) != null) {
            return false;
        }
        starSystems.add(system);
        return true;
    }

    public boolean removeStarSystem(StarSystem system) {
        return starSystems.remove(system);
    }

    public SpaceHighway buildHighway(Integer travelTime, String rampShortcut, String exitShortcut) {
        return new SpaceHighway(travelTime, getStarSystem(rampShortcut), getStarSystem(exitShortcut));
    }

    public StarSystem getStarSystem(String searchString){
        for(StarSystem system : starSystems) {
            if(system.getSystemName().equals(searchString)) return system;
            if(system.getShortcut().equals(searchString)) return system;
        }
        return null;
    }

    public String getTravelTimeOfRoute(StarSystem[] travelPoints) {
        try {
            return getNextHighway(travelPoints, 1, 0).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //TODO: Return ShortestRoute Names
    private Integer getNextHighway(StarSystem[] travelPoints, Integer nextPoint, Integer travelTime) throws Exception {
        if(travelPoints.length > nextPoint){
            List<SpaceHighway> highways = travelPoints[nextPoint-1].getRamps();
            if (!highways.isEmpty()) {
                for (SpaceHighway highway : highways) {
                    StarSystem exitPoint = highway.getExit();
                    if (exitPoint.equals(travelPoints[nextPoint])) {
                        return getNextHighway(travelPoints, nextPoint + 1, travelTime + highway.getTravelTime());
                    }
                }
            }
            throw new Exception("NO SUCH ROUTE");
        }
        return travelTime;
    }

    public void createTestWorld(){
        populateUniverse();
        buildHighways();
    }

    private void populateUniverse() {
        addStarSystem(new StarSystem("Solar System", "A"));
        addStarSystem(new StarSystem("Alpha Centauri", "B"));
        addStarSystem(new StarSystem("Sirius", "C"));
        addStarSystem(new StarSystem("Betelgeuse", "D"));
        addStarSystem(new StarSystem("Vega", "E"));
    }

    private void buildHighways() {
        buildHighway(5, "A", "B");
        buildHighway(4, "B", "C");
        buildHighway(8, "C", "D");
        buildHighway(8, "D", "C");
        buildHighway(6, "D", "E");
        buildHighway(5, "A", "D");
        buildHighway(2, "C", "E");
        buildHighway(3, "E", "B");
        buildHighway(7, "A", "E");
    }

    public List<StarSystem[]> getRoutsWithMaxStops(StarSystem start, StarSystem end, Integer maxPoints ) {
        StarRoute route = new StarRoute(start, end, maxPoints);

        List<StarRoute> results = recursiveRouteSearch(route);

        return getStarSystemsFromRoutes(results);
    }

    public List<StarSystem[]> getRoutsWithExactlyStops(StarSystem start, StarSystem end, Integer exactly ) {
        StarRoute route = new StarRoute(start, end, 6);
        List<StarRoute> results = recursiveRouteSearch(route);

        results = removeRoutesNotExactly(results, exactly);

        return getStarSystemsFromRoutes(results);
    }

    private List<StarRoute> recursiveRouteSearch(StarRoute route) {
        List<StarRoute> results = new ArrayList<>();
        if(route.isStarSystemHopPossible()) {
            for (SpaceHighway highway : route.getNextSystem().getRamps()) {
                if (!SpaceHighway.isNull(highway)) {
                    StarRoute newPath = route.copyRoute();
                    newPath.addRoutePoint(highway);

                    if (highway.getExit().equals(route.getLastSystem())) {
                        results.add(newPath);
                    }

                    results.addAll(recursiveRouteSearch(newPath));
                }
            }
        }
        return results;
    }

    private List<StarSystem[]> getStarSystemsFromRoutes(List<StarRoute> routes ) {
        List<StarSystem[]> result = new ArrayList<>();

        for(StarRoute route : routes) {
            result.add(route.getTraveledSystems());
        }
        return result;
    }

    private List<StarRoute> removeRoutesNotExactly(List<StarRoute> routes, Integer exactly) {
        List<StarRoute> rightDistance = new ArrayList<>();
        for(StarRoute route : routes){
            if(route.getSystemsInBetween().length == exactly){
                rightDistance.add(route);
            }
        }
        return rightDistance;
    }

    public int getDurationShortestRoute(StarSystem start, StarSystem end) {
        StarRoute routeWish = new StarRoute(start, end);
        List<StarRoute> results = recursiveRouteSearch(routeWish);

        Integer shortestTravelTime = Integer.MAX_VALUE;
        for(StarRoute route : results){
            Integer travelTime = route.getTravelTimeOfRoute();
            if(shortestTravelTime > travelTime) shortestTravelTime = travelTime;
        }

        return shortestTravelTime;
    }
}
