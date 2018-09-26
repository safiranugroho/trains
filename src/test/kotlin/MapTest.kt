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
    fun shouldReturnDistanceBetweenTwoCitiesIfRouteExists() {
        Map.createTowns("AB5", "AD3")
        assertEquals(5, Map.calculateDistance("AB"))
        assertEquals(3, Map.calculateDistance("AD"))
        assertEquals(null, Map.calculateDistance("BD"))
    }

    @Test
    fun shouldReturnDistanceGivenARouteWithMoreThanTwoTowns() {
        Map.createTowns("AB5", "BC4", "CD8", "DE6", "AD5", "CE2", "EB3", "AE7")
        assertEquals(5, Map.calculateDistance("AD"))
        assertEquals(22, Map.calculateDistance("AEBCD"))
        assertEquals(null, Map.calculateDistance("AED"))
    }

}
