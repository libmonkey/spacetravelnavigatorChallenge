package system;

import java.util.ArrayList;
import java.util.List;

public class Universe {

    private List<StarSystem> starSystems;

    public Universe() {
        this.starSystems = new ArrayList<StarSystem>();
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

    //TODO: Return ShortestRout Names
    private Integer getNextHighway(StarSystem[] travelPoints, Integer nextPoint, Integer travelTime) throws Exception {
        if(travelPoints.length > nextPoint){
            List<SpaceHighway> highways = travelPoints[nextPoint-1].getRamps();
            if(highways.isEmpty()){
                throw new Exception("NO SUCH ROUTE");
            } else {
                for(SpaceHighway highway : highways) {
                    StarSystem exitPoint = highway.getExit();
                    if(exitPoint.equals(travelPoints[nextPoint])){
                        return getNextHighway(travelPoints, nextPoint + 1, travelTime + highway.getTravelTime());
                    }
                }
                throw new Exception("NO SUCH ROUTE");
            }
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
}
