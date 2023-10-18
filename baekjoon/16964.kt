package `16964`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.TreeSet

val graph = Array<MutableSet<Int>>(100001) { TreeSet() }
val visited = Array<Boolean>(100001) { false }

fun dfs(cur: Int, iterator: Iterator<Int>): Boolean {
    val size = graph[cur].size + if (cur == 1) 0 else -1
    for (i in 0 until size) {
        val next = iterator.next()
        if (!graph[cur].contains(next) || visited[next])
            return false
        visited[next] = true
        if (!dfs(next, iterator))
            return false
    }
    return true
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    repeat(N - 1) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }
    val expected = reader.readLine().split(" ").map { it.toInt() }
    val iter = expected.iterator()
    visited[1] = true
    iter.next()
    val result = dfs(1, iter)
    writer.write("${if (result) 1 else 0}\n")
    writer.flush()
}