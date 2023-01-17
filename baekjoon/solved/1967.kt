package `1967`

import java.util.*

fun dfs(graph: Array<LinkedList<Pair<Int, Int>>>, visited: Array<Boolean>, now: Int, distance: Int): Pair<Int, Int> {
    var result = Pair(now, distance)
    visited[now] = true
    graph[now].filter { !visited[it.first] }.forEach {
        val temp = dfs(graph, visited, it.first, distance + it.second)
        if (temp.second > result.second)
            result = temp
    }
    visited[now] = false
    return result
}

fun main() {
    val N = readLine()!!.toInt()
    val graph = Array<LinkedList<Pair<Int, Int>>>(N) { LinkedList() }
    repeat(N - 1) {
        val (start, end, cost) = readLine()!!.split(" ").map { it.toInt() - 1 }
        graph[start].add(Pair(end, cost + 1))
        graph[end].add(Pair(start, cost + 1))
    }
    val visited = Array<Boolean>(N) { false }
    val (startNode, _) = dfs(graph, visited, 0, 0)
    repeat(N) { visited[it] = false }
    val (_, result) = dfs(graph, visited, startNode, 0)
    println(result)
}