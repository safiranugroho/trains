object Map {
    private val towns: MutableSet<Town> = HashSet()

    private fun findTown(name: Char): Town? {
        return towns.find{ it == Town(name) }
    }

    fun createTowns(vararg input: String): Set<Town> {
        for (i in input) {
            val firstTown = Town(i[0])
            val secondTown = Town(i[1])
            val distance = i[2].toString().toInt()

            towns.add(firstTown)
            towns.add(secondTown)

            towns.find{ it == firstTown }
                ?.addNeighbor(Pair(secondTown, distance))
        }

        return towns
    }

    fun calculateDistance(route: String): Int? {
        var index = 0
        var totalDistance = 0
        var current = findTown(route[index]) ?: return null

        index++

        while (index < route.length) {
            val next = findTown(route[index]) ?: return null
            totalDistance += current.hasNeighbor(next) ?: return null
            current = next

            index++
        }

        return totalDistance
    }
}