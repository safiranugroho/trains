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

            val neighbor = towns.find{ it == secondTown }!!

            towns.find{ it == firstTown }
                ?.addNeighbor(Pair(neighbor, distance))
        }

        return towns
    }

    fun createRoute(input: String): Route? {
        var index = 0
        val start = findTown(input[index]) ?: return null

        val route = Route(start)

        index++

        while (index < input.length) {
            val next = Town(input[index])
            if (!route.addStop(next)) return null

            index++
        }

        return route
    }
}