package system;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UniverseTest {
    Universe universe;

    @Before
    public void buildUniverse(){
        universe = new Universe();
        universe.createDummyUniverse();
    }

    @Test
    public void exercise_1_travelTestSolarSystemAlphaCentauriSirius(){
        StarSystem[] travelRoute = {
                universe.getStarSystem("A"),
                universe.getStarSystem("B"),
                universe.getStarSystem("C")
        };

        assertEquals("9", universe.getDurationOfRoute(travelRoute));
    }

    @Test
    public void exercise_2_travelTestSolarSystemBetelgeuse(){
        StarSystem[] travelRoute = {
                universe.getStarSystem("Solar System"),
                universe.getStarSystem("Betelgeuse")
        };

        assertEquals("5", universe.getDurationOfRoute(travelRoute));
    }

    @Test
    public void exercise_3_travelTestSolarSystemBetelgeuseSirius(){
        StarSystem[] travelRoute = {
                universe.getStarSystem("Solar System"),
                universe.getStarSystem("Betelgeuse"),
                universe.getStarSystem("Sirius")
        };
        assertEquals("13", universe.getDurationOfRoute(travelRoute));
    }

    @Test
    public void exercise_4_travelTestSolarSystemVegaAlphaCentauriSiriusBetelgeuse(){
        StarSystem[] travelRoute = {
                universe.getStarSystem("Solar System"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Betelgeuse")
        };
        assertEquals("22", universe.getDurationOfRoute(travelRoute));
    }

    @Test
    public void exercise_5_travelTestSolarSystemVegaBetelgeuse(){
        StarSystem[] travelRoute = {
                universe.getStarSystem("Solar System"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Betelgeuse")
        };
        assertEquals("NO SUCH ROUTE", universe.getDurationOfRoute(travelRoute));
    }

    @Test
    public void exercise_6_allRouteSiriusSirius(){
        StarSystem start = universe.getStarSystem("Sirius");
        StarSystem end = universe.getStarSystem("Sirius");

        List<StarSystem[]> expectedResult = new ArrayList<>();

        expectedResult.add(new StarSystem[] {
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Betelgeuse"),
                universe.getStarSystem("Sirius")
        });
        expectedResult.add(new StarSystem[]{
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius")
        });

        List<StarSystem[]> actualResult = universe.getRoutsWithMaxStops(start, end, 3);
        assertEquals(expectedResult.size(), actualResult.size());
        if(actualResult.size() > 0) {
            for (int i = 0; i < expectedResult.size(); i++) {
                assertArrayEquals(expectedResult.get(i), actualResult.get(i));
            }
        }
    }

    @Test
    public void exercise_7_getDurationOfShortestRouteSolarSystemSirius(){
        StarSystem start = universe.getStarSystem("Solar System");
        StarSystem end = universe.getStarSystem("Sirius");
        StarRoute routeWish = new StarRoute(start, end, 3);

        List<StarSystem[]> expectedResult = new ArrayList<>();

        expectedResult.add(new StarSystem[]{
                universe.getStarSystem("Solar System"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Betelgeuse"),
                universe.getStarSystem("Sirius")
        });
        expectedResult.add(new StarSystem[]{
                universe.getStarSystem("Solar System"),
                universe.getStarSystem("Betelgeuse"),
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Betelgeuse"),
                universe.getStarSystem("Sirius")
        });
        expectedResult.add(new StarSystem[]{
                universe.getStarSystem("Solar System"),
                universe.getStarSystem("Betelgeuse"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius")
        });

        List<StarSystem[]> actualResult = universe.getRoutsWithExactlyStops(start, end, 3);
        assertEquals(expectedResult.size(), actualResult.size());
        if(actualResult.size() > 0) {
            for (int i = 0; i < expectedResult.size(); i++) {
                assertArrayEquals(expectedResult.get(i), actualResult.get(i));
            }
        }

    }

    @Test
    public void exercise_8_shortestRouteSolarSystemSirius(){
        StarSystem start = universe.getStarSystem("Solar System");
        StarSystem end = universe.getStarSystem("Sirius");
        assertEquals(9, universe.getDurationShortestRoute(start, end));

    }

    @Test
    public void exercise_9_shortestRouteAlphaCentauri(){
        StarSystem system = universe.getStarSystem("Alpha Centauri");
        assertEquals(9, universe.getDurationShortestRoute(system, system));
    }

    @Test
    public void exercise_10_travelTimeLess30(){
        StarSystem system = universe.getStarSystem("Sirius");

        List<StarSystem[]> expectedResult = new ArrayList<>();

        expectedResult.add(new StarSystem[]{
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Betelgeuse"),
                universe.getStarSystem("Sirius")
        });
        expectedResult.add(new StarSystem[]{
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius")
        });
        expectedResult.add(new StarSystem[]{
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Betelgeuse"),
                universe.getStarSystem("Sirius")
        });
        expectedResult.add(new StarSystem[]{
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Betelgeuse"),
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius")
        });
        expectedResult.add(new StarSystem[]{
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Betelgeuse"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius")
        });
        expectedResult.add(new StarSystem[]{
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius")
        });
        expectedResult.add(new StarSystem[]{
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius"),
                universe.getStarSystem("Vega"),
                universe.getStarSystem("Alpha Centauri"),
                universe.getStarSystem("Sirius")
        });

        List<StarSystem[]> actualResult = universe.getRoutesWithDurationMaxLimit(system, system,30);
        assertEquals(expectedResult.size(), actualResult.size());
        for(StarSystem[] expectedElement: expectedResult){
            assertTrue(hasComparableArray(expectedElement, actualResult));
        }
    }

    private boolean hasComparableArray(StarSystem[] expectedElement, List<StarSystem[]> searchList) {
        for(StarSystem[] search: searchList){
            if(Arrays.equals(search, expectedElement)) return true;
        }
        return false;
    }

}
