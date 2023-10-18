package `1167`

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
    repeat(N) {
        val dataList = readLine()!!.split(" ").map { it.toInt() }
        val start = dataList[0] - 1
        (1 until dataList.size - 1 step 2).forEach {
            val end = dataList[it] - 1
            val cost = dataList[it + 1]
            graph[start].add(Pair(end, cost))
        }
    }
    val visited = Array<Boolean>(N) { false }
    val (startNode, _) = dfs(graph, visited, 0, 0)
    repeat(N) { visited[it] = false }
    val (_, result) = dfs(graph, visited, startNode, 0)
    println(result)
}