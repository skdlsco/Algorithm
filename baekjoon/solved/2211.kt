package `2211`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.cos

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Pair<Int, Int>>>(N + 1) { ArrayList() }
    val d = Array<Pair<Int, Int>>(N + 1) { Pair(it, Int.MAX_VALUE) }
    repeat(M) {
        val (A, B, C) = reader.readLine().split(" ").map { it.toInt() }
        graph[A].add(Pair(B, C))
        graph[B].add(Pair(A, C))
    }

    val pq = PriorityQueue<Pair<Int, Int>> { o1, o2 ->
        o1.second - o2.second
    }
    d[1] = Pair(1, 0)
    pq.add(Pair(1, 0))
    while (pq.isNotEmpty()) {
        val (cur, distance) = pq.remove()
        if (d[cur].second < distance)
            continue
        for ((next, cost) in graph[cur]) {
            val nextDistance = cost + distance
            if (nextDistance < d[next].second) {
                d[next] = Pair(cur, nextDistance)
                pq.add(Pair(next, nextDistance))
            }
        }
    }
    writer.write("${N - 1}\n")
    for (i in 2..N) {
        writer.write("$i ${d[i].first}\n")
    }
    writer.flush()
}