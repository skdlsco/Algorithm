package `15971`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import kotlin.math.max

// S에서 E로 가는 경로 반환
fun bfs(graph: Array<LinkedList<Pair<Int, Int>>>, S: Int, E: Int): Array<Int> {
    val visited = Array<Int>(graph.size) { 0 }
    // queue 자체에 pair로 위치, sum, maxCost
    val queue = LinkedList<Int>()
    visited[S] = S
    queue.add(S)
    while (queue.isNotEmpty()) {
        val now = queue.pop()
        if (now == E)
            break
        graph[now].forEach {
            if (visited[it.first] == 0) {
                visited[it.first] = now
                queue.add(it.first)
            }
        }
    }
    val path = ArrayList<Int>()
    var now = visited[E]
    while (visited[now] != now) {
        path.add(now)
        now = visited[now]
    }
    path.add(S)
    return path.toTypedArray()
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, S, E) = reader.readLine().split(" ").map { it.toInt() }
    // [s] -> (e, cost)
    val graph = Array<LinkedList<Pair<Int, Int>>>(N + 1) { LinkedList() }
    repeat(N - 1) {
        val (a, b, cost) = reader.readLine().split(" ").map { it.toInt() }
        graph[a].add(Pair(b, cost))
        graph[b].add(Pair(a, cost))
    }
    // 경로 찾기
    val path = bfs(graph, S, E)
    if (S == E) {
        println(0)
        return
    }
    // E ~ S로 가는 비용 계산
    var sum = 0
    var maxCost = 0
    var now = E
    path.forEach { next ->
        val edge = graph[now].find { it.first == next }!!
        sum += edge.second
        maxCost = max(maxCost, edge.second)
        now = next
    }
    println(sum - maxCost)
}