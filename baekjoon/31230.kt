package D

import java.util.LinkedList
import java.util.PriorityQueue

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    val (N, M, A, B) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Pair<Int, Long>>>(200001) { ArrayList() }
    repeat(M) {
        val (a, b, c) = reader.readLine().split(" ").map { it.toLong() }
        val u = a.toInt()
        val v = b.toInt()
        graph[u].add(Pair(v, c))
        graph[v].add(Pair(u, c))
    }
    val visited = Array<Long>(200001) { Long.MAX_VALUE }
    val path = Array<ArrayList<Int>>(200001) { ArrayList() }
    val pq = PriorityQueue<Pair<Int, Long>>() { o1, o2 ->
        o1.second.compareTo(o2.second)
    }
    visited[A] = 0
    pq.add(Pair(A, 0L))
    while (pq.isNotEmpty()) {
        val (cur, cost) = pq.remove()
        if (visited[cur] < cost)
            continue
        for ((next, c) in graph[cur]) {
            if (visited[next] > c + cost) {
                visited[next] = c + cost
                path[next] = ArrayList()
                path[next].add(cur)
                pq.add(Pair(next, c + cost))
            } else if (visited[next] == c + cost) {
                path[next].add(cur)
            }
        }
    }
    val route = ArrayList<Int>()
    val checked = Array<Boolean>(N + 1) { false }
    val q = ArrayDeque<Int>()
    for (next in path[B]) {
        checked[next] = true
    }
    q.addAll(path[B])
    while (q.isNotEmpty()) {
        val cur = q.removeFirst()
        route.add(cur)
        for (next in path[cur]) {
            if (!checked[next]) {
                q.add(next)
                checked[next] = true
            }
        }
    }
    route.add(B)
    route.sort()
    writer.write("${route.size}\n")
    writer.write(route.joinToString(" "))
    writer.flush()
}
    