data class Town(val name: Char) {
    private val neighbors: MutableList<Pair<Town, Int>> = ArrayList()

    fun addNeighbor(neighbor: Pair<Town, Int>) {
        neighbors.add(neighbor)
    }

    fun getNeighbors(): List<Pair<Town, Int>> {
        return neighbors
    }

    fun hasNeighbor(town: Town): Pair<Town, Int>? {
        return neighbors.find { it.first == town }
    }
}
