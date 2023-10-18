package `1956`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (V, E) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<Array<Int>>(V + 1) { Array(V + 1) { Int.MAX_VALUE } }
    repeat(E) {
        val (a, b, c) = reader.readLine().split(" ").map { it.toInt() }
        graph[a][b] = c
    }
    for (m in 1..V) {
        for (s in 1..V) {
            for (e in 1..V) {
                if (graph[s][m] == Int.MAX_VALUE || graph[m][e] == Int.MAX_VALUE)
                    continue
                if (graph[s][e] > graph[s][m] + graph[m][e])
                    graph[s][e] = graph[s][m] + graph[m][e]
            }
        }
    }
    val result = (1..V).minOf { graph[it][it] }
    writer.write("${if (result == Int.MAX_VALUE) -1 else result}\n")
    writer.flush()
}