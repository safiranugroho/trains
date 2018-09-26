data class Town(val name: Char) {
    private val neighbors: MutableList<Pair<Town, Int>> = ArrayList()

    fun getNeighbors(): List<Pair<Town, Int>> {
        return this.neighbors
    }

    fun addNeighbor(neighbor: Pair<Town, Int>) {
        this.neighbors.add(neighbor)
    }
}
