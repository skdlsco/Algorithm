package `30206`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

val MOD = 1_000_000_007
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Int>>(N) { ArrayList() }
    repeat(M) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() - 1 }
        graph[u].add(v)
        graph[v].add(u)
    }
    val visited = Array<Boolean>(N) { false }
    val distance = Array<Long>(N) { 0L }
    var ans = 1L
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(0, 0))
    visited[0] = true
    while (queue.isNotEmpty()) {
        val (d, cur) = queue.pop()
        distance[d]++
        for (next in graph[cur]) {
            if (!visited[next]) {
                queue.add(Pair(d + 1, next))
                visited[next] = true
            }
        }
    }
    for (i in 0 until N) {
        val temp = (ans * distance[i]) % MOD
        ans = (ans + temp) % MOD
    }
    writer.write("${ans - 1}\n")
    writer.flush()
}
    