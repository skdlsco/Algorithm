package `2982`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Edge(val L: Int, var godolTime: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val (A, B, K, G) = reader.readLine().split(" ").map { it.toInt() }
    val godolList = reader.readLine().trim().split(" ").map { it.toInt() }

    val graph = Array<Array<Edge>>(N + 1) { Array(N + 1) { Edge(-1, -1) } }
    repeat(M) {
        val (U, V, L) = reader.readLine().split(" ").map { it.toInt() }
        graph[U][V] = Edge(L, -1)
        graph[V][U] = Edge(L, -1)
    }

    if (godolList.size > 1) {
        var prev = godolList.first()
        var t = 0
        for (i in 1 until G) {
            val cur = godolList[i]
            graph[cur][prev].godolTime = t
            graph[prev][cur].godolTime = t
            t += graph[cur][prev].L
            prev = cur
        }
    }
    val visited = Array<Boolean>(N + 1) { false }
    val map = Array<Int>(N + 1) { Int.MAX_VALUE }
    map[A] = K
    for (i in 1..N) {
        var minValue = Int.MAX_VALUE
        var cur = -1
        for (idx in 1..N) {
            if (!visited[idx] && map[idx] < minValue) {
                cur = idx
                minValue = map[idx]
            }
        }
        visited[cur] = true
        for (next in 1..N) {
            val edge = graph[cur][next]
            if (edge.L == -1 || visited[next])
                continue
            var nextDist = minValue + edge.L
            if (edge.godolTime >= 0 && minValue in edge.godolTime until edge.godolTime + edge.L)
                nextDist = edge.godolTime + edge.L + edge.L
            map[next] = minOf(map[next], nextDist)
        }
    }
    writer.write("${map[B] - K}\n")
    writer.flush()
}