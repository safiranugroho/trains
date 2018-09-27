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

    fun getRoutes(start: Char, end: Char, maxStops: Int): List<Route> {
        return getFilteredRoutes(end, getAllPossibleRoutes(start, end, maxStops))
    }

    fun getShortestRoute(start: Char, end: Char): Route {
        val queue: MutableList<Town> = ArrayList()
        val visited: MutableSet<Town> = HashSet()

        val meta: MutableMap<Town, Pair<Town?, Int?>> = HashMap()

        val startingTown = towns.find { it == Town(start) }!!
        meta.set(startingTown, Pair(null, null))
        queue.add(startingTown)

        while (queue.isNotEmpty()) {
            val nextTown = queue.removeAt(0)

            if (nextTown == Town(end)) {
                return constructRoute(nextTown, meta)
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

    private fun getAllPossibleRoutes(start: Char, end: Char, maxStops: Int): List<Route> {
        val possibleRoutes: MutableList<Route> = ArrayList()

        val startingTown = towns.find { it == Town(start)}!!

        if (maxStops == 0) {
            val route = Route(startingTown)
            possibleRoutes.add(route)
            return possibleRoutes
        } else {
            var routes: List<Route> = ArrayList()
            val neighbors = startingTown.getNeighbors()
            for (neighbor in neighbors) {
                routes = routes.plus(getAllPossibleRoutes(neighbor.first.name, end, maxStops - 1))
            }

            for (route in routes) {
                route.addStart(startingTown)
            }

            return routes
        }
    }

    private fun getFilteredRoutes(end: Char, routes: List<Route>): List<Route> {
        val filteredRoutes: MutableList<Route> = ArrayList()

        for (route in routes) {
            filteredRoutes.add(route.reverse())
        }

        return filteredRoutes.filter { it.getDestination() == Town(end) }
    }

    private fun constructRoute(destination: Town, meta: MutableMap<Town, Pair<Town?, Int?>>): Route {
        val stringBuilder = StringBuilder()
        stringBuilder.append(destination.name)

        var currentTown = destination

        while (meta[currentTown]?.first != null) {
            currentTown = meta[currentTown]?.first!!

            stringBuilder.append(currentTown.name)
        }

        return createRoute(stringBuilder.reverse().toString())!!
    }
}