package `1238`

import java.util.*
import kotlin.math.max

fun dijkstra(graph: Array<ArrayList<Pair<Int, Int>>>, start: Int): Array<Int> {
    val dijkstra = Array<Int>(graph.size) { Int.MAX_VALUE - 1 };
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    dijkstra[start] = 0
    queue.add(Pair(start, 0))
    while (queue.isNotEmpty()) {
        val (now, cost) = queue.remove()

        graph[now].forEach {
            if (dijkstra[it.first] > cost + it.second) {
                dijkstra[it.first] = cost + it.second
                queue.add(Pair(it.first, dijkstra[it.first]))
            }
        }
    }
    return dijkstra
}

fun main() {
    val (N, M, X) = readLine()!!.split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Pair<Int, Int>>>(N + 1) { ArrayList() }
    repeat(M) {
        val (start, end, cost) = readLine()!!.split(" ").map { it.toInt() }
        graph[start].add(Pair(end, cost))
    }
    val fromX = dijkstra(graph, X)
    var result = 0
    (1..N).forEach {
        val nCost = dijkstra(graph, it)[X] + fromX[it]
        result = max(nCost, result)
    }
    println(result)
}