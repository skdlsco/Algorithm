package `1368`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.min

fun solve(N: Int, nodeCost: Array<Int>, edges: Array<ArrayList<Pair<Int, Int>>>): Int {
    val added = Array<Boolean>(N) { false }
    val pq = PriorityQueue<Pair<Int, Int>>() { a, b -> a.first - b.first }
    for (i in 0 until N) {
        pq.add(Pair(nodeCost[i], i))
    }
    var result = 0
    for (i in 0 until N) {
        var now = -1
        var minDist = Int.MAX_VALUE
        while (pq.isNotEmpty()) {
            val (cost, node) = pq.remove()
            if (!added[node]) {
                now = node
                minDist = cost
                break
            }
        }
        result += minDist
        added[now] = true
        nodeCost[now] = Int.MAX_VALUE
        for (edge in edges[now])
            pq.add(edge)
    }
    return result
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    // 논 내는 비용
    val nodeCost = Array<Int>(N) { 0 }
    // cost, (u, v)
    val edges = Array<ArrayList<Pair<Int, Int>>>(N) { ArrayList() }
    for (i in 0 until N) {
        val cost = reader.readLine().toInt()
        nodeCost[i] = cost
    }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N) {
            if (y != x)
                edges[y].add(Pair(row[x], x))
        }
    }
    val result = solve(N, nodeCost, edges)
    writer.write("${result}\n")
    writer.flush()
}