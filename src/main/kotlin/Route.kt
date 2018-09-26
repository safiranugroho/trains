class Route(start: Town) {
    private val stops: MutableList<Town> = arrayListOf(start)
    private var distance: Int = 0

    fun addStop(town: Town): Boolean {
        val neighbor = stops.last().hasNeighbor(town) ?: return false
        stops.add(neighbor.first)
        distance += neighbor.second

        return true
    }

    fun getStops(): List<Town> {
        return stops
    }

    fun getDistance(): Int {
        return distance
    }

    fun getNumberOfStops(): Int {
        return stops.size - 1
    }
}
