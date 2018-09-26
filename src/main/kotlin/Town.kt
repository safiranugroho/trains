data class Town(val name: Char) {
    lateinit var neighbor: Town

    fun getNeighbors(): Town {
        return neighbor
    }

    fun addNeighbor(town: Town) {
        neighbor = town
    }
}
