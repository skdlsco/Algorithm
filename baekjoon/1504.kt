package `1504`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.min

fun dijkstra(graph: Array<ArrayList<Pair<Int, Int>>>, start: Int): Array<Int> {
    val dijkstra = Array<Int>(graph.size) { -1 };
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    dijkstra[start] = 0
    queue.add(Pair(start, 0))
    while (queue.isNotEmpty()) {
        val (now, cost) = queue.remove()

        graph[now].forEach {
            if (dijkstra[it.first] == -1 || dijkstra[it.first] > cost + it.second) {
                dijkstra[it.first] = cost + it.second
                queue.add(Pair(it.first, dijkstra[it.first]))
            }
        }
    }
    return dijkstra
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine()!!.split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Pair<Int, Int>>>(N + 1) { ArrayList() }
    repeat(M) {
        val (start, end, cost) = reader.readLine()!!.split(" ").map { it.toInt() }
        graph[start].add(Pair(end, cost))
        graph[end].add(Pair(start, cost))
    }
    val (target1, target2) = reader.readLine()!!.split(" ").map { it.toInt() }
    val fromStart = dijkstra(graph, 1)
    val fromTarget1 = dijkstra(graph, target1)
    val fromTarget2 = dijkstra(graph, target2)

    val result1 =
        if (fromStart[target1] == -1 || fromTarget1[target2] == -1 || fromTarget2[N] == -1)
            -1
        else
            fromStart[target1] + fromTarget1[target2] + fromTarget2[N]
    val result2 =
        if (fromStart[target2] == -1 || fromTarget2[target1] == -1 || fromTarget1[N] == -1)
            -1
        else
            fromStart[target2] + fromTarget2[target1] + fromTarget1[N]
    if (result1 == -1 && result2 == -1)
        println(-1)
    else if (result1 == -1)
        println(result2)
    else if (result2 == -1)
        println(result1)
    else
        println(min(result1, result2))
}