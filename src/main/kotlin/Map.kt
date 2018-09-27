object Map {
    private val towns: MutableSet<Town> = HashSet()

    private fun findTown(name: Char): Town? {
        return towns.find{ it == Town(name) }
    }

    fun createTowns(vararg input: String): Set<Town> {
        towns.clear()

        for (i in input) {
            val firstTown = Town(i[0])
            val secondTown = Town(i[1])
            val distance = i[2].toString().toInt()

            towns.add(firstTown)
            towns.add(secondTown)

            val neighbor: Town = findTown(i[1])!!
            findTown(i[0])?.addNeighbor(Pair(neighbor, distance))
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

    fun getRoutes(start: Char, end: Char, noOfStops: Int): List<Route> {
        val routes: MutableList<Route> = ArrayList()

        for (route in getFilteredRoutes(end, getAllPossibleRoutes(start, end, noOfStops))) {
            routes.add(createRoute(route)!!)
        }

        return routes
    }

    fun getRoutesWithMaxStops(start: Char, end: Char, maxStops: Int): List<Route> {
        var index = maxStops
        var routes: List<Route> = ArrayList()

        while (index > 0) {
            routes = routes.plus(getRoutes(start, end, index))
            index--
        }

        return routes
    }

    fun getShortestRoute(start: Char, end: Char): Route {
        val queue: MutableList<Town> = ArrayList()
        val visited: MutableSet<Town> = HashSet()

        val meta: MutableMap<Town, Pair<Town?, Int?>> = HashMap()

        val startingTown: Town = findTown(start)!!
        meta.set(startingTown, Pair(null, null))
        queue.add(startingTown)

        while (queue.isNotEmpty()) {
            val nextTown = queue.removeAt(0)

            if (nextTown == Town(end)) {
                return createRoute(constructRoute(nextTown, meta))!!
            }

            for (neighbor in nextTown.getNeighbors()) {
                if (visited.contains(neighbor.first)) {
                    continue
                }

                if (!queue.contains(neighbor.first)) {
                    meta[neighbor.first] = Pair(nextTown, neighbor.second)
                    queue.add(neighbor.first)
                }
            }

            visited.add(nextTown)
        }

        return Route(Town(start))
    }


    private fun getAllPossibleRoutes(start: Char, end: Char, noOfStops: Int): List<StringBuilder> {
        val possibleRoutes: MutableList<StringBuilder> = ArrayList()
        val startingTown: Town = findTown(start)!!

        if (noOfStops == 0) {
            possibleRoutes.add(StringBuilder().append(start))

            return possibleRoutes
        } else {
            var routes: List<StringBuilder> = ArrayList()
            for (neighbor in startingTown.getNeighbors()) {
                routes = routes.plus(getAllPossibleRoutes(neighbor.first.name, end, noOfStops - 1))
            }

            val appendedRoutes: MutableList<StringBuilder> = ArrayList()
            for (route in routes) {
                appendedRoutes.add(route.append(start))
            }

            return appendedRoutes
        }
    }

    private fun getFilteredRoutes(end: Char, routes: List<StringBuilder>): List<String> {
        val filteredRoutes: MutableList<String> = ArrayList()

        for (route in routes) {
            filteredRoutes.add(route.reverse().toString())
        }

        return filteredRoutes.filter{ it.last() == end }
    }

    private fun constructRoute(destination: Town, meta: MutableMap<Town, Pair<Town?, Int?>>): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(destination.name)

        var currentTown = destination

        while (meta[currentTown]?.first != null) {
            currentTown = meta[currentTown]?.first!!
            stringBuilder.append(currentTown.name)
        }

        return stringBuilder.reverse().toString()
    }
}