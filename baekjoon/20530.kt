package `20530`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

val set = Array<Int>(200001) { it }

fun union(a: Int, b: Int) {
    val aRoot = find(a)
    val bRoot = find(b)
    if (aRoot == bRoot)
        return
    set[bRoot] = aRoot
}

fun find(node: Int): Int {
    if (set[node] != node)
        set[node] = find(set[node])
    return set[node]
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, Q) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Int>>(N + 1) { ArrayList() }
    val inDegreeArr = Array<Int>(N + 1) { 0 }
    repeat(N) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
        inDegreeArr[a]++
        inDegreeArr[b]++
    }
    val queue = LinkedList<Int>()
    for (i in 1..N) {
        if (inDegreeArr[i] <= 1)
            queue.add(i)
    }
    // 사이클 외 간선 지우기
    while (queue.isNotEmpty()) {
        val cur = queue.remove()
        inDegreeArr[cur]--
        for (next in graph[cur]) {
            inDegreeArr[next]--
            if (inDegreeArr[next] == 1)
                queue.add(next)
        }
    }
    for (i in 1..N) {
        if (inDegreeArr[i] >= 1)
            continue
        for (j in graph[i]) {
            union(j, i)
        }
    }
    repeat(Q) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() }
        if (find(u) == find(v))
            writer.write("1\n")
        else
            writer.write("2\n")
    }
    writer.flush()
}