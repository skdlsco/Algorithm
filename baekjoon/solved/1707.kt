package `1707`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun isBipartiteGraph(V: Int, graph: Array<LinkedList<Int>>): Boolean {
    val vertexColors = Array<Int>(V + 1) { -1 }
    // vertex, color {0, 1}
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(1, 0))
    vertexColors[1] = 0
    while (queue.isNotEmpty()) {
        val (cur, color) = queue.pop()
        val nextColor = (color + 1) % 2
        for (next in graph[cur]) {
            if (vertexColors[next] == -1) {
                vertexColors[next] = nextColor
                queue.add(Pair(next, nextColor))
            } else if (vertexColors[next] != nextColor)
                return false
        }
        // 모든 노드가 연결되어 있지 않는 경우
        if (queue.isEmpty()) {
            val emptyIndex = vertexColors.indexOf(-1)
            if (emptyIndex != -1) {
                queue.add(Pair(emptyIndex, 0))
                vertexColors[emptyIndex] = 0
            }
        }
    }
    return true
}

fun solve() {
    val (V, E) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<LinkedList<Int>>(V + 1) { LinkedList() }
    repeat(E) {
        val (s, e) = reader.readLine().split(" ").map { it.toInt() }
        graph[s].add(e)
        graph[e].add(s)
    }
    if (isBipartiteGraph(V, graph))
        writer.write("YES\n")
    else
        writer.write("NO\n")
}

fun main() {
    val K = reader.readLine().toInt()
    repeat(K) {
        solve()
    }
    writer.flush()
}