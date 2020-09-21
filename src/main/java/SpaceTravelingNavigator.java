import system.SpaceHighway;
import system.StarSystem;
import system.Universe;

import java.util.ArrayList;
import java.util.List;

public class SpaceTravelingNavigator {

    public static void main(String[] args) {
        Universe universe = new Universe();
        universe.createTestWorld();

        System.out.println("Entfernung Test: " +  universe.getTravelTimeOfRoute(new StarSystem[]{universe.getStarSystem("A"), universe.getStarSystem("B")}));

    }
}
