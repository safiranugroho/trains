import org.junit.Assert.*
import org.junit.Test

class TownTest {

    @Test
    fun shouldBeAbleToAddOneNeighbor() {
        val town = Town('B')
        town.addNeighbor(Town('C'))
        assertEquals(Town('C'), town.getNeighbors())
    }

}