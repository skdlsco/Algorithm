package `1162`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.PriorityQueue

data class Data(val node: Int, val k: Int, val d: Long)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Pair<Int, Long>>>(N + 1) { ArrayList() }

    repeat(M) {
        val (u, v, w) = reader.readLine().split(" ").map { it.toInt() }
        graph[u].add(Pair(v, w.toLong()))
        graph[v].add(Pair(u, w.toLong()))
    }
    // k개의 도로를 포장하여 i에 도착하는 최소비용
    val dist = Array<Array<Long>>(K + 1) { Array(N + 1) { Long.MAX_VALUE } }
    // (node, (k, distance))
    val pq = PriorityQueue<Data>() { o1, o2 ->
        if (o1.k == o2.k)
            o1.d.compareTo(o2.d)
        else
            o1.k.compareTo(o2.k)
    }
    dist[0][1] = 0L
    pq.add(Data(1, 0, 0L))
    while (pq.isNotEmpty()) {
        val (node, k, d) = pq.remove()

        if (dist[k][node] < d)
            continue
        for ((next, cost) in graph[node]) {
            if (k < K && d < dist[k + 1][next]) {
                dist[k + 1][next] = d
                pq.add(Data(next, k + 1, d))
            }
            if (d + cost < dist[k][next]) {
                dist[k][next] = d + cost
                pq.add(Data(next, k, d + cost))
            }
        }
    }
    writer.write("${(0..K).minOf { dist[it][N] }}\n")
    writer.flush()
}