package `19701`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue

data class Edge(val y: Int, val t: Long, val k: Long)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (V, E) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Edge>>(V + 1) { ArrayList() }
    repeat(E) {
        val (x, y, t, k) = reader.readLine().split(" ").map { it.toLong() }
        graph[x.toInt()].add(Edge(y.toInt(), t, k))
        graph[y.toInt()].add(Edge(x.toInt(), t, k))
    }
    val dist = Array<Long>(V + 1) { Long.MAX_VALUE }
    val eatDist = Array<Long>(V + 1) { Long.MAX_VALUE }
    val pq = PriorityQueue<Pair<Int, Long>>() { o1, o2 ->
        if (o1.second < o2.second) -1
        else if (o1.second > o2.second) 1
        else 0
    }
    dist[1] = 0
    eatDist[1] = 0
    pq.add(Pair(1, 0))
    // 돈까스 안먹는 경로
    while (pq.isNotEmpty()) {
        val (node, c) = pq.remove()
        if (dist[node] < c)
            continue
        for (next in graph[node]) {
            if (c + next.t < dist[next.y]) {
                pq.add(Pair(next.y, c + next.t))
                dist[next.y] = c + next.t
            }
            if (c + next.t - next.k < eatDist[next.y])
                eatDist[next.y] = c + next.t - next.k
        }
    }
    pq.clear()
    for (i in 1..V) {
        pq.add(Pair(i, eatDist[i]))
    }
    while (pq.isNotEmpty()) {
        val (node, c) = pq.remove()
        if (dist[node] < c)
            continue
        for (next in graph[node]) {
            if (c + next.t < eatDist[next.y]) {
                pq.add(Pair(next.y, c + next.t))
                eatDist[next.y] = c + next.t
            }
        }
    }
    for (i in 2..V) {
        writer.write("${eatDist[i]}\n")
    }
    writer.flush()
}