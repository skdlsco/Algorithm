package `27653`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun dfs(graph: Array<ArrayList<Int>>, target: Array<Long>, cur: Int, prev: Int, w: Long): Long {
    var sum = max(target[cur] - w, 0)

    for (next in graph[cur]) {
        if (next == prev)
            continue
        sum += dfs(graph, target, next, cur, target[cur])
    }
    return sum
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val weight = Array<Long>(N) { 0 }
    val graph = Array<ArrayList<Int>>(N) { ArrayList() }

    reader.readLine().split(" ").map { it.toLong() }.forEachIndexed { idx, w -> weight[idx] = w }
    repeat(N - 1) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() - 1 }
        graph[u].add(v)
        graph[v].add(u)
    }
    writer.write("${dfs(graph, weight, 0, 0, 0L)}\n")
    writer.flush()
}
    