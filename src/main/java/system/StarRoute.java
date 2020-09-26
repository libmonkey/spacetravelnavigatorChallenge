package system;

import java.lang.reflect.Array;
import java.util.Arrays;

public class StarRoute {
    private final StarSystem firstSystem;
    private final StarSystem lastSystem;
    private SpaceHighway[] routePoints;
    private Integer travelPoint = -1;

    /**
     * Constructor for StarRoute with known start, end and allowed number of highways.
     * @param firstSystem first StarSystem of travel route
     * @param lastSystem last StarSystem of travel route
     * @param routeLength allowed maximum number of used highways
     */
    public StarRoute(StarSystem firstSystem, StarSystem lastSystem, Integer routeLength) {
        this.firstSystem = firstSystem;
        this.lastSystem = lastSystem;
        this.routePoints = new SpaceHighway[routeLength];
        this.travelPoint = -1;
    }

    /**
     * Constructor for StarRoute with known start and end.
     * Allowed number of highway is set to 10.
     * @param firstSystem first StarSystem of travel route
     * @param lastSystem last StarSystem of travel route
     */
    public StarRoute(StarSystem firstSystem, StarSystem lastSystem) {
        this.firstSystem = firstSystem;
        this.lastSystem = lastSystem;
        this.routePoints = new SpaceHighway[10];
    }


    /**
     * Add Highway to Route.
     * @param point traveled Highway
     */
    public void addRoutePoint(SpaceHighway point) {
        addTravelPoint();
        routePoints[travelPoint] =  point;
    }

    /**
     * Remove the last added highway from StarRoute.
     */
    public void removeLastRoutePoint() {
        routePoints[travelPoint] = null;
        removeTravelPoint();
    }

    /**
     * Checks if maximum limit of allowed highways is reached.
     * @return true if limit is not reached, else false.
     */
    public Boolean isStarSystemHopPossible(){
        return travelPoint + 1 < routePoints.length;
    }

    /**
     * Gives start position of StarRoute.
     * @return first StarSystem of route
     */
    public StarSystem getFirstSystem() {
        return firstSystem;
    }

    /**
     * Gives end position of StarRoute.
     * @return last StarSystem of route
     */
    public StarSystem getLastSystem() {
        return lastSystem;
    }

    /**
     * Gives StarSystem of last highway exit.
     * @return actual StarSystem
     */
    public StarSystem getNextSystem(){
        if(routePoints[0] == null) {
            return getFirstSystem();
        }
        return routePoints[travelPoint].getExit();
    }

    /**
     * Create duplicate of current StarRoute with routePoints and current position.
     * @return copy of this StarRoute
     */
    public StarRoute copyRoute(){
        StarRoute newStarRoute = new StarRoute(firstSystem, lastSystem, routePoints.length);
        newStarRoute.routePoints = this.routePoints.clone();
        newStarRoute.travelPoint = this.travelPoint;
        return newStarRoute;
    }

    /**
     * Get all traveled StarSystems with start, end, and points in between.
     * @return all traveled StarSystems in order
     */
    public StarSystem[] getTraveledSystems() {
        StarSystem[] traveledSystems = new StarSystem[travelPoint + 2];
        traveledSystems[0] = firstSystem;
        for(Integer i = 0; i < travelPoint + 1; i++){
            traveledSystems[i+1] = routePoints[i].getExit();
        }
        return traveledSystems;
    }

    /**
     * Get all traveled StarSystems of route between start- and endpoint.
     * @return all StarSystems exclude start and end.
     */
    public StarSystem[] getSystemsInBetween() {
        StarSystem[] traveledSystems = getTraveledSystems();
        return Arrays.copyOfRange(traveledSystems, 1, traveledSystems.length - 1);
    }

    /**
     *
     * @return travel time as Integer
     * @throws Exception NO SUCH ROUTE if route has no highways.
     */
    public Integer duration() throws Exception{
        if(routePoints.length == 0) {
            throw new Exception("NO SUCH ROUTE");
        }

        Integer travelTime = 0;
        for(SpaceHighway highway: routePoints) {
            travelTime += highway.getTravelTime();
        }
        return travelTime;
    }

    /**
     * Get traveled time from route.
     * @return travel time as Integer
     */
    public Integer getTravelTimeOfRoute() {
        Integer travelTime = 0;
        for(SpaceHighway highway: routePoints) {
            if(!SpaceHighway.isNull(highway)) {
                travelTime += highway.getTravelTime();
            }
        }
        return travelTime;
    }

    // Private methods ---------------------------------------------------------------------------------------------

    private void addTravelPoint() {
        if (isStarSystemHopPossible()) travelPoint++;
        else throw new ArrayIndexOutOfBoundsException("Size of allowed star system hops reached.");
    }

    private void removeTravelPoint() {
        if (travelPoint > 0) travelPoint--;
        else throw new ArrayIndexOutOfBoundsException("No star systems as route points");
    }
}
