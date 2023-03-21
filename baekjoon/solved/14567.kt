package `14567`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val result = Array<Int>(N + 1) { 0 }
    val graph = Array<LinkedList<Int>>(N + 1) { LinkedList() }
    val inDegreeArr = Array<Int>(N + 1) { 0 }
    repeat(M) {
        val (start, end) = reader.readLine().split(" ").map { it.toInt() }
        graph[start].add(end)
        inDegreeArr[end]++
    }
    // node, depth
    val queue = LinkedList<Pair<Int, Int>>()
    for (i in 1..N) {
        if (inDegreeArr[i] == 0)
            queue.add(Pair(i, 1))
    }
    while (queue.isNotEmpty()) {
        val (node, depth) = queue.remove()
        result[node] = depth
        graph[node].forEach {
            inDegreeArr[it]--
            if (inDegreeArr[it] == 0)
                queue.add(Pair(it, depth + 1))
        }
    }
    for (i in 1..N) {
        writer.append("${result[i]} ")
    }
    writer.newLine()
    writer.flush()
}