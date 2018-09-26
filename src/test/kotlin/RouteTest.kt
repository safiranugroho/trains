import org.junit.Assert.*
import org.junit.Test

class RouteTest {

    @Test
    fun shouldCreateRouteWithStartingTown() {
        val route = Route(Town('A'))
        assertEquals(arrayListOf(Town('A')), route.getStops())
    }

    @Test
    fun shouldReturnNumberOfStopsExclusingStartingTown() {
        Map.createTowns("AB5", "BC4", "CD8", "DE6", "AD5", "CE2", "EB3", "AE7")
        val route = Map.createRoute("AEBCD")
        assertEquals(4, route?.getNumberOfStops())
    }

    @Test
    fun shouldOnlyAddStopIfPreviousTownHasItAsNeighbor() {
        val towns = Map.createTowns("AB5", "BC4", "CD8", "DE6", "AD5", "CE2", "EB3", "AE7")
        val town = towns.find{ it == Town('A') }

        val route = Route(town!!)
        assertTrue(route.addStop(Town('B')))
        assertTrue(route.addStop(Town('C')))
        assertTrue(route.addStop(Town('D')))
        assertFalse(route.addStop(Town('B')))
    }

    @Test
    fun shouldReturnTotalDistanceAfterRouteIsSuccessfullyCreated() {
        val towns = Map.createTowns("AB5", "BC4", "EB3", "CD8", "DE6", "AD5", "CE2", "AE7")
        val town = towns.find{ it == Town('A') }

        val route = Route(town!!)
        route.addStop(Town('B'))
        assertEquals(5, route.getDistance())

        route.addStop(Town('C'))
        assertEquals(9, route.getDistance())

        route.addStop(Town('A'))
        assertEquals(9, route.getDistance())
    }
}