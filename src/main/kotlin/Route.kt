class Route(start: Town) {
    private var stops: MutableList<Town> = arrayListOf(start)
    private var distance: Int = 0

    fun addStart(town: Town): Boolean {
        val neighbor = town.hasNeighbor(stops.last()) ?: return false
        stops.add(town)
        distance += neighbor.second

        return true
    }

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

    fun getDestination(): Town {
        return stops.last()
    }

    fun reverse(): Route {
        stops = stops.reversed() as MutableList<Town>
        return this
    }

    override operator fun equals(other: Any?): Boolean {
        return (other is Route && other.getStops() == this.getStops())
    }
}
