package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import java.util.StringTokenizer
import java.util.TreeSet

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    while (true) {
        val (M, N) = reader.readLine().split(" ").map { it.toInt() }
        if (M == 0 && N == 0)
            break
        val graph = Array<ArrayList<Pair<Int, Long>>>(M) { ArrayList() }
        var sum = 0L
        repeat(N) {
            val (x, y, z) = reader.readLine().split(" ").map { it.toInt() }
            graph[x].add(Pair(y, z.toLong()))
            graph[y].add(Pair(x, z.toLong()))
            sum += z
        }
        val pq = PriorityQueue<Pair<Int, Long>>() { o1, o2 ->
            o1.second.compareTo(o2.second)
        }
        val visited = Array<Boolean>(M) { false }
        var mstSum = 0L
        pq.add(Pair(0, 0L))
        for (i in 0 until M) {
            var cur = -1
            var cost = Long.MAX_VALUE
            while (pq.isNotEmpty()) {
                val (n, c) = pq.remove()
                cur = n
                if (!visited[cur]) {
                    cost = c
                    break
                }
            }
            mstSum += cost
            visited[cur] = true
            for (edge in graph[cur]) {
                pq.add(edge)
            }
        }
        writer.write("${sum - mstSum}\n")
    }
    writer.flush()
}
    