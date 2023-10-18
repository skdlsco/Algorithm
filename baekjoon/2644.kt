package `2644`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    val (s, e) = reader.readLine().split(" ").map { it.toInt() }
    val m = reader.readLine().toInt()
    val graph = Array<ArrayList<Int>>(n + 1) { ArrayList() }
    repeat(m) {
        val (x, y) = reader.readLine().split(" ").map { it.toInt() }
        graph[x].add(y)
        graph[y].add(x)
    }
    val visited = Array<Boolean>(n + 1) { false }
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(s, 0))
    visited[s] = true
    var result = -1
    while (queue.isNotEmpty()) {
        val (cur, depth) = queue.remove()
        if (cur == e) {
            result = depth
            break
        }
        for (next in graph[cur]) {
            if (!visited[next]) {
                queue.add(Pair(next, depth + 1))
                visited[next] = true
            }
        }
    }
    writer.write("${result}\n")
    writer.flush()
}