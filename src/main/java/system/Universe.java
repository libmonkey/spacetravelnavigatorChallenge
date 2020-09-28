package system;

import java.util.*;

public class Universe {

    private List<StarSystem> starSystems;

    /**
     * Constructor that creates an empty universe.
     */
    public Universe() {
        this.starSystems = new ArrayList<>();
    }

    /**
     * Add StarSystem to Universe.
     * @param system new StarSystem
     * @return true if new StarSystem was successfully added
     */
    private boolean addStarSystem(StarSystem system) {
        if(getStarSystem(system.getShortcut()) != null) {
            return false;
        }
        starSystems.add(system);
        return true;
    }

    /**
     * Return StarSystem with given name or shortcut.
     * @param searchString name or shortcut of System
     * @return StarSystem or null
     */
    public StarSystem getStarSystem(String searchString){
        for(StarSystem system : starSystems) {
            if(system.getSystemName().equals(searchString)) return system;
            if(system.getShortcut().equals(searchString)) return system;
        }
        return null;
    }

    /**
     * Returns Distance of given travel route.
     * @param spaceRoute Array of StarSystems that are traveled
     * @return travel time as string
     */
    public String getDistanceOfRoute(StarSystem[] spaceRoute){
        StarRoute route = new StarRoute(spaceRoute[0], spaceRoute[spaceRoute.length -1], spaceRoute.length - 1);
        int currentSystem = 0;
        while(currentSystem + 1 < spaceRoute.length) {
            SpaceHighway highway = spaceRoute[currentSystem].getHighwayToStarSystem(spaceRoute[currentSystem + 1]);
            if(SpaceHighway.isNull(highway)) return "NO SUCH ROUTE";
            else route.addRoutePoint(highway);
            currentSystem++;
        }
        return route.getTravelTimeOfRoute().toString();
    }

    /**
     * Returns all routes from start to end with equals or less highways.
     * @param start route starts here
     * @param end route should end here
     * @param maxPoints allowed maximum of used highways
     * @return List of Routes
     */
    public List<StarSystem[]> getRoutsWithMaxStops(StarSystem start, StarSystem end, Integer maxPoints ) {
        StarRoute route = new StarRoute(start, end, maxPoints);

        List<StarRoute> results = recursiveRouteSearch(route);

        return getStarSystemsFromRoutes(results);
    }

    /**
     * Returns all routes of given start to end with exactly number of used highways.
     * @param start route starts here
     * @param end route should end here
     * @param exactly allowed number of highways
     * @return List of routes
     */
    public List<StarSystem[]> getRoutsWithExactlyStops(StarSystem start, StarSystem end, Integer exactly ) {
        StarRoute route = new StarRoute(start, end, 6);
        List<StarRoute> results = recursiveRouteSearch(route);

        results = removeRoutesNotExactly(results, exactly);

        return getStarSystemsFromRoutes(results);
    }

    /**
     * Return the duration of the shortest route from start to end.
     * @param start route starts here
     * @param end route should end here
     * @return List of routes
     */
    public int getDurationShortestRoute(StarSystem start, StarSystem end) {
        StarRoute routeWish = new StarRoute(start, end);
        List<StarRoute> results = recursiveRouteSearch(routeWish);

        int shortestTravelTime = Integer.MAX_VALUE;
        for(StarRoute route : results){
            Integer travelTime = route.getTravelTimeOfRoute();
            if(shortestTravelTime > travelTime) shortestTravelTime = travelTime;
        }

        return shortestTravelTime;
    }

    /**
     * Return all routes that takes less than given time.
     * @param start route starts here
     * @param end route should end here
     * @param maximumTravelTime limit of maximum travel time
     * @return List of routes
     */
    public List<StarSystem[]> getTravelTimeMaxLimit(StarSystem start, StarSystem end, Integer maximumTravelTime) {
        StarRoute routeWish = new StarRoute(start, end, 13);
        List<StarSystem[]> result = new ArrayList<>();
        List<StarRoute> results = recursiveRouteSearch(routeWish);

        for(StarRoute route : results){
            if(route.getTravelTimeOfRoute() < maximumTravelTime) {
                result.add(route.getTraveledSystems());
            }
        }
        return result;
    }

    /**
     * Create universe with dummy data.
     */
    public void createDummyUniverse(){
        populateUniverse();
        buildHighways();
    }

    // Private methods ---------------------------------------------------------------------------------------------

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

    private SpaceHighway buildHighway(Integer travelTime, String rampShortcut, String exitShortcut) {
        return new SpaceHighway(travelTime, getStarSystem(rampShortcut), getStarSystem(exitShortcut));
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
}
