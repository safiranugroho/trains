import org.junit.Assert.*
import org.junit.Test

class MapTest {

    @Test
    fun shouldParseTownFromStringInput() {
        val towns: Set<Town> = Map.createTowns("AB5")
        assertTrue(towns.contains(Town('A')))
        assertTrue(towns.contains(Town('B')))
    }

    @Test
    fun shouldParseMultipleTownsFromStringInputWithoutDuplication() {
        val towns: Set<Town> = Map.createTowns("AB5", "BC4")
        assertTrue(towns.contains(Town('A')))
        assertTrue(towns.contains(Town('B')))
        assertTrue(towns.contains(Town('C')))
        assertEquals(3, towns.size)
    }

    @Test
    fun shouldCreateAndConnectTownsWithDistanceFromStringInput() {
        val towns: Set<Town> = Map.createTowns("AB5", "BC4")
        assertEquals(arrayListOf(Pair(Town('B'), 5)), towns.find { it == Town('A') }?.getNeighbors())
        assertEquals(arrayListOf(Pair(Town('C'), 4)), towns.find { it == Town('B') }?.getNeighbors())
    }

    @Test
    fun shouldCreateRouteWithMultipleTownsAndGetDistance() {
        Map.createTowns("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")

        var route = Map.createRoute("AD")
        assertEquals(5, route?.getDistance())

        route = Map.createRoute("AEBCD")
        assertEquals(22, route?.getDistance())

        route = Map.createRoute("AED")
        assertEquals(null, route?.getDistance())
    }

    @Test
    fun shouldCreateAllPossibleRoutesGivenOneStopAndDestination() {
        Map.createTowns("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")

        val trips: List<Route> = Map.getRoutes('A', 'D', 1)

        assertEquals(1, trips.size)
        assertTrue(trips.contains(Map.createRoute("AD")))
    }

    @Test
    fun shouldCreateAllPossibleRoutesGivenExactNoOfStopsAndDestination() {
        Map.createTowns("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")

        val trips: List<Route> = Map.getRoutes('A', 'C', 4)

        assertEquals(3, trips.size)
        assertTrue(trips.contains(Map.createRoute("ABCDC")))
    }


    @Test
    fun shouldCreateShortestRouteGivenTwoDestinations() {
        Map.createTowns("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")

        val route = Map.getShortestRoute('A', 'C')
        assertEquals(2, route.getNumberOfStops())
        assertEquals(Map.createRoute("ABC"), route)
    }

    @Test
    fun shouldCreateAllPossibleRoutesGivenMaxStopsAndDestination() {
        Map.createTowns("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")

        val trips: List<Route> = Map.getRoutesWithMaxStops('C', 'C', 3)

        assertEquals(2, trips.size)
        assertTrue(trips.contains(Map.createRoute("CDC")))
    }
}
