import org.junit.Assert.*
import org.junit.Test

class RouteTest {

    @Test
    fun shouldCreateRouteWithStartingTown() {
        val route = Route(Town('A'))
        assertEquals(arrayListOf(Town('A')), route.getStops())
    }

    @Test
    fun shouldReturnTotalDistanceWhenAddingAnotherStop() {
        val route = Route(Town('A'))

        route.addStop(Pair(Town('B'), 5))
        assertEquals(5, route.getDistance())

        route.addStop(Pair(Town('C'), 4))
        assertEquals(9, route.getDistance())
    }
}