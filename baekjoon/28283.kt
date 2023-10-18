package `28283`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, X, Y) = reader.readLine().split(" ").map { it.toInt() }
    val amountArr = reader.readLine().split(" ").map { it.toLong() }
    val graph = Array<ArrayList<Int>>(N) { ArrayList() }
    repeat(M) {
        val (S, E) = reader.readLine().split(" ").map { it.toInt() - 1 }
        graph[S].add(E)
        graph[E].add(S)
    }
    val timeoutArr = Array<Int>(N) { -1 }
    val visited = Array<Boolean>(N) { false }
    val queue = LinkedList<Pair<Int, Int>>()

    // entry
    reader.readLine().split(" ").map { it.toInt() - 1 }.forEach {
        visited[it] = true
        queue.add(Pair(it, 0))
    }
    while (queue.isNotEmpty()) {
        val (cur, time) = queue.remove()
        timeoutArr[cur] = time
        for (next in graph[cur]) {
            if (!visited[next]) {
                queue.add(Pair(next, time + 1))
                visited[next] = true
            }
        }
    }

    if ((0 until N).any { timeoutArr[it] == -1 && amountArr[it] > 0 }) {
        writer.write("${-1}\n")
    } else {
        val ans = timeoutArr.zip(amountArr)
                .map { it.first * it.second }
                .sortedDescending().subList(0, X).sumOf { it }
        writer.write("${ans}\n")
    }
    writer.flush()
}
    