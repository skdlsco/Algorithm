package `1260`

import java.util.*

fun dfs(graph: Array<LinkedList<Int>>, now: Int, visited: Array<Boolean>) {
    print("$now ")
    visited[now] = true
    graph[now].forEach {
        if (!visited[it])
            dfs(graph, it, visited)
    }
}

fun bfs(graph: Array<LinkedList<Int>>, start: Int) {
    val visited = Array(graph.size) { false }
    visited[start] = true
    val queue = LinkedList<Int>()
    queue.add(start)
    while (queue.isNotEmpty()) {
        val node = queue.pop()
        print("$node ")
        graph[node].forEach {
            if (!visited[it]) {
                queue.add(it)
                visited[it] = true
            }
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val (N, M, V) = scanner.nextLine().split(" ").map { it.toInt() }
    val graph = Array<LinkedList<Int>>(N + 1) { LinkedList() }

    repeat(M) {
        val s = scanner.nextInt()
        val e = scanner.nextInt()

        graph[s].add(e)
        graph[e].add(s)
    }
    graph.forEach { it.sort() }
    dfs(graph, V, Array(N + 1) { false })
    println()
    bfs(graph, V)
}