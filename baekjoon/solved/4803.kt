package `4803`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

fun countTree(n: Int, graph: Array<LinkedList<Int>>): Int {
    val visited = Array<Boolean>(n + 1) { false }
    var count = 0
    for (i in 1..n) {
        if (!visited[i]) {
            val isTree = checkIsTreeByBfs(graph, visited, i)
            if (isTree)
                count++
        }
    }
    return count
}

fun checkIsTreeByBfs(graph: Array<LinkedList<Int>>, visited: Array<Boolean>, start: Int): Boolean {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    var hasCycle = false
    queue.add(Pair(start, start))
    visited[start] = true

    while (queue.isNotEmpty()) {
        val (prev, now) = queue.remove()
        for (next in graph[now]) {
            if (visited[next] && prev != next)
                hasCycle = true
            if (!visited[next]) {
                visited[next] = true
                queue.add(Pair(now, next))
            }
        }
    }
    return !hasCycle
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var case = 1
    while (true) {
        val (n, m) = reader.readLine().split(" ").map { it.toInt() }
        if (n == 0 && m == 0)
            break
        val graph = Array<LinkedList<Int>>(n + 1) { LinkedList() }
        for (i in 0 until m) {
            val (a, b) = reader.readLine().split(" ").map { it.toInt() }
            graph[a].add(b)
            graph[b].add(a)
        }
        val result = countTree(n, graph)
        writer.write("Case $case: ")
        when (result) {
            0 -> writer.write("No trees.")
            1 -> writer.write("There is one tree.")
            else -> writer.write("A forest of $result trees.")
        }
        writer.newLine()
        case++
    }
    writer.flush()
}