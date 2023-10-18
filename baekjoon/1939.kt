package `1939`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.min

//fun main() {
//    val reader = BufferedReader(InputStreamReader(System.`in`))
//    val writer = BufferedWriter(OutputStreamWriter(System.out))
//    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
//    val graph = Array<ArrayList<Pair<Int, Int>>>(N + 1) { ArrayList() }
//    repeat(M) {
//        val (A, B, C) = reader.readLine().split(" ").map { it.toInt() }
//        graph[A].add(Pair(B, C))
//        graph[B].add(Pair(A, C))
//    }
//    val (S, E) = reader.readLine().split(" ").map { it.toInt() }
//    val visited = Array<Int>(N + 1) { 0 }
//    val pq = PriorityQueue<Pair<Int, Int>>() { o1, o2 ->
//        o2.second - o1.second
//    }
//    visited[S] = Int.MAX_VALUE
//    var ans = 0
//    pq.addAll(graph[S])
//    while (pq.isNotEmpty()) {
//        val (node, c) = pq.remove()
//
//        if (node == E) {
//            ans = c
//            break
//        }
//        for ((next, cost) in graph[node]) {
//            val minCost = min(c, cost)
//            if (visited[next] < minCost) {
//                pq.add(Pair(next, minCost))
//                visited[next] = minCost
//            }
//        }
//    }
//    writer.write("${ans}\n")
//    writer.flush()
//}

val set = Array<Int>(100001) { it }

fun union(a: Int, b: Int) {
    val aRoot = find(a)
    val bRoot = find(b)
    if (aRoot == bRoot)
        return
    set[bRoot] = aRoot
}

fun find(a: Int): Int {
    if (a == set[a])
        return set[a]
    val root = find(set[a])
    set[a] = root
    return set[a]
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val edges = ArrayList<Pair<Int, Pair<Int, Int>>>()
    repeat(M) {
        val (A, B, C) = reader.readLine().split(" ").map { it.toInt() }
        edges.add(Pair(C, Pair(A, B)))
    }
    edges.sortByDescending { it.first }
    val (S, E) = reader.readLine().split(" ").map { it.toInt() }
    var ans = 0
    for ((c, p) in edges) {
        val (a, b) = p
        union(a, b)
        if (find(S) == find(E)) {
            ans = c
            break
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}