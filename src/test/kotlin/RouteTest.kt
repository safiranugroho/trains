import org.junit.Assert.*
import org.junit.Test

class RouteTest {
    private val towns = Map.createTowns("AB5", "BC4", "EB3", "CD8", "DC8", "DE6", "AD5", "CE2", "AE7")

    @Test
    fun shouldCreateRouteWithStartingTown() {
        val route = Route(Town('A'))
        assertEquals(arrayListOf(Town('A')), route.getStops())
    }

    @Test
    fun shouldReturnNumberOfStopsExclusingStartingTown() {
        val route = Map.createRoute("AEBCD")
        assertEquals(4, route?.getNumberOfStops())
    }

    @Test
    fun shouldOnlyAddStopIfPreviousTownHasItAsNeighbor() {
        val town = towns.find{ it == Town('A') }

        val route = Route(town!!)
        assertTrue(route.addStop(Town('B')))
        assertTrue(route.addStop(Town('C')))
        assertTrue(route.addStop(Town('D')))
        assertFalse(route.addStop(Town('B')))
    }

    @Test
    fun shouldReturnTotalDistanceAfterRouteIsSuccessfullyCreated() {
        val town = towns.find{ it == Town('A') }

        val route = Route(town!!)
        route.addStop(Town('B'))
        assertEquals(5, route.getDistance())

        route.addStop(Town('C'))
        assertEquals(9, route.getDistance())

        route.addStop(Town('A'))
        assertEquals(9, route.getDistance())
    }

    @Test
    fun shouldReturnDestinationOfRoute() {
        val route = Map.createRoute("ABCD")!!
        assertEquals(Town('D'), route.getDestination())
    }

    @Test
    fun shouldOnlyAddStartIfNextTownHasItAsNeighbor() {
        val town = towns.find{ it == Town('B') }
        val stop = towns.find{ it == Town('A') }

        val route = Route(town!!)
        assertTrue(route.addStart(stop!!))
    }

    @Test
    fun shouldReverseRouteFromDestinationToStart() {
        val town = towns.find{ it == Town('C') }
        val route = Route(town!!)

        route.addStop(Town('D'))
        assertEquals(Map.createRoute("DC"), route.reverse())
    }
}