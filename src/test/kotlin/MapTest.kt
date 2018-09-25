import org.junit.Assert.*
import org.junit.Test

class MapTest {

    @Test
    fun shouldParseEdgeFromStringInput() {
        val towns: ArrayList<Town> = Map.createTown("AB5")
        assertEquals(Town("A"), towns[0])
        assertEquals(Town("B"), towns[1])
    }
}
