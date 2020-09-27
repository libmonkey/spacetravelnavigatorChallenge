import system.StarSystem;
import system.Universe;

public class SpaceTravelingNavigator {

    public static void main(String[] args) {
        Universe universe = new Universe();
        universe.createDummyUniverse();

        System.out.println("Entfernung Test: " +  universe.getTravelTimeOfRoute(new StarSystem[]{universe.getStarSystem("A"), universe.getStarSystem("B")}));
    }
}
