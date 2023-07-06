package `4386`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sqrt

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    val stars = Array<Pair<Double, Double>>(n) { Pair(0.0, 0.0) }
    repeat(n) {
        val (x, y) = reader.readLine().split(" ").map { it.toDouble() }
        stars[it] = Pair(x, y)
    }
    val graph = Array<Array<Double>>(n) { Array(n) { 0.0 } }
    for (i in 0 until n) {
        for (j in 0 until n) {
            val x = abs(stars[i].first - stars[j].first)
            val y = abs(stars[i].second - stars[j].second)
            graph[i][j] = sqrt(x * x + y * y)
        }
    }
    var res = 0.0
    val visited = Array<Boolean>(n) { false }
    val dist = Array<Double>(n) { Double.MAX_VALUE }
    dist[0] = 0.0
    for (i in 0 until n) {
        var idx = -1
        var minCost = 0.0
        for (j in 0 until n) {
            if (!visited[j] && (idx == -1 || dist[j] < minCost)) {
                idx = j
                minCost = dist[j]
            }
        }
        if (idx == -1)
            return
        res += minCost
        visited[idx] = true
        for (j in 0 until n) {
            dist[j] = min(dist[j], graph[idx][j])
        }
    }
    writer.write("${res}\n")
    writer.flush()
}