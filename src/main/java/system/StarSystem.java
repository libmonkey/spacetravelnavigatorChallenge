package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StarSystem {

    private String systemName;
    private String shortcut;
    private List<SpaceHighway> ramps;
    private List<SpaceHighway> exits;

    public StarSystem(String systemName, String shortcut) {
        this.systemName = systemName;
        this.shortcut = shortcut;
        this.ramps = new ArrayList<>();
        this.exits = new ArrayList<>();
    }

    public void addExit(SpaceHighway exit) {
        exits.add(exit);
    }

    public void addRamp(SpaceHighway ramp) {
        ramps.add(ramp);
    }

    public String getSystemName() {
        return systemName;
    }

    public String getShortcut() {
        return shortcut;
    }

    /**
     * Checks if star system is connected to highway and removes ramp or exit.
     * @param highway that is to be removed from ramps or exit
     * @return highway was removed
     */
    public boolean removeHighway(SpaceHighway highway){
        if(ramps.remove(highway)) return true;
        return exits.remove(highway);
    }

    public List<SpaceHighway> getRamps() {
        return Collections.unmodifiableList(ramps);
    }

    public List<SpaceHighway> getExits() {
        return Collections.unmodifiableList(exits);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StarSystem that = (StarSystem) o;

        if (!systemName.equals(that.systemName)) return false;
        if (!ramps.equals(that.ramps)) return false;
        return exits.equals(that.exits);
    }

    @Override
    public int hashCode() {
        int result = systemName.hashCode();
        result = 31 * result + ramps.hashCode();
        result = 31 * result + exits.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "Star System: " +
                systemName +
                " Shortcut: " +
                shortcut;
    }

    public SpaceHighway getHighwayToStarSystem(StarSystem goal){
        for(SpaceHighway highway : getRamps()){
            if(highway.getExit().equals(goal)) return highway;
        }
        return null;
    }
}
