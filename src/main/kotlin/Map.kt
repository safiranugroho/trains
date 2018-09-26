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

            towns.find{ it == firstTown }!!
                 .addNeighbor(Pair(secondTown, distance))
        }

        return towns
    }

    fun createRoute(input: String): Route? {
        var index = 0
        var current = findTown(input[index]) ?: return null

        val route = Route(current)

        index++

        while (index < input.length) {
            val next = findTown(input[index]) ?: return null
            val neighbor = current.hasNeighbor(next) ?: return null

            route.addStop(neighbor)
            current = next

            index++
        }

        return route
    }
}