package `1197`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (V, E) = reader.readLine().split(" ").map { it.toInt() }
    val edges = Array<ArrayList<Pair<Int, Int>>>(V + 1) { ArrayList() }
    val visited = Array<Boolean>(V + 1) { false }
    val pq = PriorityQueue<Pair<Int, Int>>() { o1, o2 -> o1.first - o2.first }
    repeat(E) {
        val (A, B, C) = reader.readLine().split(" ").map { it.toInt() }
        edges[A].add(Pair(C, B))
        edges[B].add(Pair(C, A))
    }
    var sum = 0L
    pq.add(Pair(0, 1))
    for (i in 0 until V) {
        var cur = 0
        var minDist = Int.MAX_VALUE
        while (pq.isNotEmpty()) {
            val (cost, node) = pq.remove()
            if (!visited[node]) {
                cur = node
                minDist = cost
                break
            }
        }
        sum += minDist
        visited[cur] = true
        for (next in edges[cur]) {
            pq.add(next)
        }
    }
    writer.write("${sum}")
    writer.flush()
}