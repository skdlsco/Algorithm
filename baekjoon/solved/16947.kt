package `16947`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun findCycle(
    graph: Array<LinkedList<Int>>,
    visited: Array<Boolean>,
    dest: Array<Boolean>,
    cur: Int,
    prev: Int
): Int {
    for (next in graph[cur]) {
        if (next == prev)
            continue
        if (visited[next]) {
            dest[cur] = true
            return next
        }
        visited[next] = true
        val result = findCycle(graph, visited, dest, next,cur)
        if (result != -1) {
            dest[cur] = true
            if (cur == result)
                return -1
            return result
        }
    }
    return -1
}

fun updateDepth(graph: Array<LinkedList<Int>>, depthArr: Array<Int>, cur: Int, prev: Int, depth: Int) {
    depthArr[cur] = depth
    for (next in graph[cur]) {
        if (next == prev)
            continue
        updateDepth(graph, depthArr, next, cur, depth + 1)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val graph = Array<LinkedList<Int>>(N + 1) { LinkedList() }

    repeat(N) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }
    val visited = Array<Boolean>(N + 1) { false }
    val isInCycle = Array<Boolean>(N + 1) { false }
    visited[1] = true
    findCycle(graph, visited, isInCycle, 1, 1)
    val depthArr = Array<Int>(N + 1) { 0 }
    for (i in 1..N) {
        if (isInCycle[i]) {
            for (next in graph[i]) {
                if (!isInCycle[next])
                    updateDepth(graph, depthArr, next, i, 1)
            }
        }
    }

    for (i in 1..N) {
        writer.write("${depthArr[i]} ")
    }
    writer.flush()
}