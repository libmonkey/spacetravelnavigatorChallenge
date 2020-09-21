package system;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UniverseTest {
    Universe universe;

    @Before
    public void buildUniverse(){
        universe = new Universe();
        universe.createTestWorld();
    }

    @Test
    public void travelTestSolarSystemAlphaCentauriSirius(){
        StarSystem[] travelRoute = {
                universe.getStarSystem("A"),
                universe.getStarSystem("B"),
                universe.getStarSystem("C")
        };

        assertEquals("9", universe.getTravelTimeOfRoute(travelRoute));
    }

    @Test
    public void travelTestSolarSystemBetelgeuse(){
        StarSystem[] travelRoute = {
                universe.getStarSystem("Solar System"),
                universe.getStarSystem("Betelgeuse")
        };

        assertEquals("5", universe.getTravelTimeOfRoute(travelRoute));
    }

    @Test
    public void travelTestSolarSystemBetelgeuseSirius(){
        StarSystem[] travelRoute = {
                universe.getStarSystem("Solar System"),
                universe.getStarSystem("Betelgeuse"),
                universe.getStarSystem("Sirius")
        };
        assertEquals("13", universe.getTravelTimeOfRoute(travelRoute));
    }

    @Test
    public void travelTestSolarSystemVegaAlphaCentauriSiriusBetelgeuse(){
        StarSystem[] travelRoute = {
                universe.getStarSystem("Solar System"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Betelgeuse")
        };
        assertEquals("22", universe.getTravelTimeOfRoute(travelRoute));
    }

    @Test
    public void travelTestSolarSystemVegaBetelgeuse(){
        StarSystem[] travelRoute = {
                universe.getStarSystem("Solar System"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Betelgeuse")
        };
        assertEquals("NO SUCH ROUTE", universe.getTravelTimeOfRoute(travelRoute));
    }

    @Test
    public void allRouteSiriusSirius(){
        StarSystem[] travelRoute = {
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Sirius")
        };
        List expectedResult = Arrays.asList(new StarSystem[]{
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Betelgeuse"),
                universe.getStarSystem("Sirius")
                },
                new StarSystem[]{
                        universe.getStarSystem("Sirius"),
                        universe.getStarSystem("Vega"),
                        universe.getStarSystem("Alpha Centauri"),
                        universe.getStarSystem("Sirius")
                }
        );
        List actualResult = universe.getRoutsWithMaxStops(universe.getStarSystem("Sirius"), universe.getStarSystem("Sirius"), 3);
        assertEquals();
        
    }

}
