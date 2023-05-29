package `28073`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val startNode = N
    val endNode = N + 1
    val (S, E) = reader.readLine().split(" ").map { it[0] }
    val graph = Array<ArrayList<Int>>(N + 2) { ArrayList() }
    val stamps = Array<Char>(N + 2) { ' ' }
    reader.readLine().forEachIndexed { index, c ->
        stamps[index] = c
    }
    // 시작 노드와 끝 노드 묶기
    for (i in 0 until N) {
        if (stamps[i] == S)
            graph[startNode].add(i)
        if (stamps[i] == E)
            graph[i].add(endNode)
    }
    repeat(M) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() - 1 }
        graph[u].add(v)
        graph[v].add(u)
    }
    val route = Array<Int>(N + 2) { -1 }
    val queue = LinkedList<Int>()
    queue.add(startNode)
    while (queue.isNotEmpty()) {
        val cur = queue.pop()
        if (cur == endNode)
            break
        var prevStamp = '0'
        var prev = -1
        for (next in graph[cur].sortedBy { stamps[it] }) {
            if (route[next] == -1) {
                if (stamps[next] == prevStamp) {
                    graph[prev].addAll(graph[next])
                    graph[next].clear()
                } else {
                    route[next] = cur
                    queue.add(next)
                    prev = next
                }
                prevStamp = stamps[next]
            }
        }
    }
    if (route[endNode] == -1)
        writer.write("Aaak!")
    else {
        var cur = route[endNode]
        val path = StringBuilder()
        while (cur != startNode) {
            path.append(stamps[cur])
            cur = route[cur]
        }
        writer.write(path.reverse().toString())
    }
    writer.flush()
}