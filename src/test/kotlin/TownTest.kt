import org.junit.Assert.*
import org.junit.Test

class TownTest {

    @Test
    fun shouldBeAbleToAddOneNeighborWithDistance() {
        val town = Town('B')
        town.addNeighbor(Pair(Town('C'), 4))
        assertEquals(arrayListOf(Pair(Town('C'), 4)), town.getNeighbors())
    }

    @Test
    fun shouldReturnMultipleNeighborsWithDistances() {
        val town = Town('B')
        val firstNeighbor = Pair(Town('C'), 4)
        val secondNeighbor = Pair(Town('A'), 3)
        town.addNeighbor(firstNeighbor)
        town.addNeighbor(secondNeighbor)

        val expected = arrayListOf(firstNeighbor, secondNeighbor)

        assertEquals(expected, town.getNeighbors())
    }

}