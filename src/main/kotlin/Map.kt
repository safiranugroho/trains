object Map {
    private val towns: MutableSet<Town> = HashSet()

    fun createTowns(vararg input: String): Set<Town> {
        for (i in input) {
            val firstTown = Town(i[0])
            val secondTown = Town(i[1])

            towns.add(firstTown)
            towns.add(secondTown)

            towns.find{ it == firstTown }
                ?.addNeighbor(secondTown)
        }

        return towns
    }
}
