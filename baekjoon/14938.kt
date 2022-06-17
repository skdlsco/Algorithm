package `14938`

import java.util.LinkedList
import java.util.PriorityQueue

data class Edge(val target: Int, val distance: Int)

fun dijkstra(itemMap: Array<Int>, graph: Array<LinkedList<Edge>>, start: Int, m: Int): Int {
    val map = Array<Int>(itemMap.size) { Int.MAX_VALUE }
    map[start] = 0
    val queue = PriorityQueue<Edge>(compareBy { it.distance })
    queue.add(Edge(start, 0))

    while (queue.isNotEmpty()) {
        val edge = queue.remove()
        graph[edge.target].forEach {
            val nextDis = edge.distance + it.distance
            if (nextDis < map[it.target] && nextDis <= m) {
                map[it.target] = nextDis
                queue.add(Edge(it.target, nextDis))
            }
        }
    }
    var sum = 0
    map.forEachIndexed { i, distance ->
        if (
            distance <= m)
            sum += itemMap[i]
    }
    return sum
}

fun main() {
    val (n, m, r) = readLine()!!.split(" ").map { it.toInt() }
    val itemMap = readLine()!!.split(" ").map { it.toInt() }.toTypedArray()
    val graph = Array<LinkedList<Edge>>(n) { LinkedList() }

    repeat(r) {
        val (s, e, d) = readLine()!!.split(" ").map { it.toInt() - 1 }
        graph[s].add(Edge(e, d + 1))
        graph[e].add(Edge(s, d + 1))
    }
    val result = (0 until n).maxOf {
        dijkstra(itemMap, graph, it, m)
    }
    println(result)
}