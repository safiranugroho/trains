class Route(start: Town) {
    private val stops: MutableList<Town> = arrayListOf(start)
    private var distance: Int = 0

    fun addStop(stop: Pair<Town, Int>) {
        stops.add(stop.first)
        distance += stop.second
    }

    fun getStops(): List<Town> {
        return stops
    }

    fun getDistance(): Int {
        return distance
    }
}
