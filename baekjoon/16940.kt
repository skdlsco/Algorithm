package `16940`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.TreeSet

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val graph = Array<LinkedList<Int>>(N + 1) { LinkedList() }
    repeat(N - 1) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }
    val sequence = reader.readLine().split(" ").map { it.toInt() }
    var idx = 1
    val visited = Array<Boolean>(N + 1) { false }
    visited[1] = true
    val queue = LinkedList<Int>()
    queue.add(1)
    var isValid = true
    val set = TreeSet<Int>()
    while (queue.isNotEmpty() && isValid) {
        val cur = queue.pop()
        val size = graph[cur].size - if (cur == 1) 0 else 1
        set.addAll(graph[cur])
        for (i in 0 until size) {
            val next = sequence[idx]
            if (visited[next] || !set.contains(next)) {
                isValid = false
                break
            } else {
                queue.add(next)
                visited[next] = true
            }
            idx++
        }
        set.clear()
    }
    writer.write("${if (isValid) 1 else 0}\n")
    writer.flush()
}