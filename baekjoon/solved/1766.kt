package `1766`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.PriorityQueue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val inDegreeArr = Array<Int>(N + 1) { 0 }
    val graph = Array<ArrayList<Int>>(N + 1) { ArrayList() }
    repeat(M) {
        val (A, B) = reader.readLine().split(" ").map { it.toInt() }
        inDegreeArr[B]++
        graph[A].add(B)
    }
    val pq = PriorityQueue<Int>()
    val result = ArrayList<Int>()
    for (i in 1..N) {
        if (inDegreeArr[i] == 0)
            pq.add(i)
    }
    while (pq.isNotEmpty()) {
        val cur = pq.remove()
        result.add(cur)
        for (next in graph[cur]) {
            inDegreeArr[next]--
            if (inDegreeArr[next] == 0)
                pq.add(next)
        }
    }
    writer.write("${result.joinToString(" ")}\n")
    writer.flush()
}