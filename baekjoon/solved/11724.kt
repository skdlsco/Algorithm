package `11724`
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun dfs(visited: Array<Boolean>, graph: Array<LinkedList<Int>>, now: Int) {
    graph[now].forEach {
        if (!visited[it]) {
            visited[it] = true
            dfs(visited, graph, it)
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<LinkedList<Int>>(N + 1) { LinkedList() }
    val visited = Array<Boolean>(N + 1) { false }
    visited[0] = true
    repeat(M) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() }
        graph[u].push(v)
        graph[v].push(u)
    }
    var cnt = 0
    while (!visited.all { it }) {
        val start = visited.indexOfFirst { !it }
        visited[start] = true
        dfs(visited, graph, start)
        cnt++
    }
    println(cnt)
}