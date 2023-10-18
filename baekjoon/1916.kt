package `1916`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val M = reader.readLine().toInt()
    val graph = Array<ArrayList<Pair<Int, Int>>>(N + 1) { ArrayList() }

    repeat(M) {
        val (start, end, cost) = reader.readLine().split(" ").map { it.toInt() }
        graph[start].add(Pair(end, cost))
    }
    val (start, end) = reader.readLine().split(" ").map { it.toInt() }
    val costArray = Array<Int>(N + 1) { Int.MAX_VALUE }
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    costArray[start] = 0
    queue.add(Pair(start, 0))
    while (queue.isNotEmpty()) {
        val (pos, cost) = queue.remove()
        if (cost > costArray[pos])
            continue
        graph[pos].forEach {
            if (costArray[it.first] > it.second + cost) {
                costArray[it.first] = it.second + cost
                queue.add(Pair(it.first, costArray[it.first]))
            }
        }
    }
    println(costArray[end])
}