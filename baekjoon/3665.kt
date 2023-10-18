package `3665`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun solve(): List<Int> {
    val n = reader.readLine().toInt()
    val rank = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<Array<Boolean>>(n + 1) { Array(n + 1) { false } }
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            graph[rank[i]][rank[j]] = true
        }
    }
    val m = reader.readLine().toInt()
    repeat(m) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        graph[a][b] = !graph[a][b]
        graph[b][a] = !graph[b][a]
    }
    val adj = Array<ArrayList<Int>>(n + 1) { ArrayList() }
    val inDegreeArr = Array<Int>(n + 1) { 0 }
    for (i in 1..n) {
        for (j in 1..n) {
            if (graph[i][j]) {
                adj[i].add(j)
                inDegreeArr[j]++
            }
        }
    }

    val queue = LinkedList<Int>()
    for (i in 1..n) {
        if (inDegreeArr[i] == 0)
            queue.add(i)
    }
    val sorted = ArrayList<Int>()
    while (queue.isNotEmpty()) {
        val cur = queue.pop()
        sorted.add(cur)
        for (next in adj[cur]) {
            inDegreeArr[next]--
            if (inDegreeArr[next] == 0)
                queue.add(next)
        }
    }
    if (sorted.size < n)
        return emptyList()
    return sorted
}

fun main() {
    val t = reader.readLine().toInt()
    repeat(t) {
        val result = solve()
        if (result.isEmpty())
            writer.write("IMPOSSIBLE")
        else
            writer.write(result.joinToString(" "))
        writer.newLine()
    }
    writer.flush()
}