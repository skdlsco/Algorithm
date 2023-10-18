package `19538`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val graph = Array<ArrayList<Int>>(N + 1) { ArrayList() }
    val condition = Array<Int>(N + 1) { 0 }
    for (cur in 1..N) {
        val next = reader.readLine().split(" ").map { it.toInt() }
        for (i in 0 until next.lastIndex) {
            graph[cur].add(next[i])
        }
        condition[cur] = (next.size - 1) / 2
        if ((next.size - 1) % 2 == 1)
            condition[cur]++
    }
    val M = reader.readLine().toInt()
    val start = reader.readLine().split(" ").map { it.toInt() }
    // t, cur
    val queue = LinkedList<Pair<Int, Int>>()
    val visited = Array<Boolean>(N + 1) { false }
    start.forEach {
        queue.add(Pair(0, it))
        visited[it] = true
    }
    val ans = Array<Int>(N + 1) { -1 }
    while (queue.isNotEmpty()) {
        val (t, cur) = queue.pop()
        ans[cur] = t
        for (next in graph[cur]) {
            condition[next]--
            if (condition[next] <= 0 && !visited[next]) {
                queue.add(Pair(t + 1, next))
                visited[next] = true
            }
        }
    }
    for (i in 1..N) {
        writer.write("${ans[i]} ")
    }
    writer.flush()
}