package system;

public class StarRoute {
    private final StarSystem firstSystem;
    private final StarSystem lastSystem;
    private SpaceHighway[] routePoints;
    private Integer travelPoint = -1;

    public StarRoute(StarSystem firstSystem, StarSystem lastSystem, Integer routeLength) {
        this.firstSystem = firstSystem;
        this.lastSystem = lastSystem;
        this.routePoints = new SpaceHighway[routeLength];
        this.travelPoint = -1;
    }

    public StarRoute(StarSystem start, StarSystem end) {
        this.firstSystem = start;
        this.lastSystem = end;
        this.routePoints = new SpaceHighway[10];
    }

    public StarRoute(StarSystem start, StarSystem end, SpaceHighway[] routePoints) {
        this.firstSystem = start;
        this.lastSystem = end;
        this.routePoints = routePoints;
        this.travelPoint = routePoints.length - 1;
    }

    public void addRoutePoint(SpaceHighway point) {
        addTravelPoint();
        routePoints[travelPoint] =  point;
    }

    public void removeLastRoutePoint() {
        routePoints[travelPoint] = null;
        removeTravelPoint();
    }

    public Boolean isStarSystemHopPossible(){
        return travelPoint + 1 < routePoints.length;
    }

    public StarSystem getFirstSystem() {
        return firstSystem;
    }

    public StarSystem getLastSystem() {
        return lastSystem;
    }

    public StarSystem getNextSystem(){
        if(routePoints[0] == null) {
            return firstSystem;
        }
        return routePoints[travelPoint].getExit();
    }

    private void addTravelPoint() {
        if (isStarSystemHopPossible()) travelPoint++;
        else throw new ArrayIndexOutOfBoundsException("Size of allowed star system hops reached.");
    }

    private void removeTravelPoint() {
        if (travelPoint > 0) travelPoint--;
        else throw new ArrayIndexOutOfBoundsException("No star systems as route points");
    }

    public StarRoute copyRoute(){
        StarRoute newStarRoute = new StarRoute(firstSystem, lastSystem, routePoints.length);
        newStarRoute.routePoints = this.routePoints.clone();
        newStarRoute.travelPoint = this.travelPoint;
        return newStarRoute;
    }

    public StarSystem[] getTraveledSystems() {
        StarSystem[] traveledSystems = new StarSystem[travelPoint + 2];
        traveledSystems[0] = firstSystem;
        for(Integer i = 0; i < travelPoint + 1; i++){
            traveledSystems[i+1] = routePoints[i].getExit();
        }
        return traveledSystems;
    }

    public StarSystem[] getSystemsInBetween() {
        StarSystem[] inBetween = new StarSystem[travelPoint];
        for(Integer i = 0; i < inBetween.length; i++){
            inBetween[i] = routePoints[i].getExit();
        }
        return inBetween;
    }

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

    public Integer getTravelTimeOfRoute() {
        Integer travelTime = 0;
        for(SpaceHighway highway: routePoints) {
            if(!SpaceHighway.isNull(highway)) {
                travelTime += highway.getTravelTime();
            }
        }
        return travelTime;
    }
}
