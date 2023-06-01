package `11562`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

val DEFAULT = 123456789
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<Array<Int>>(N + 1) { Array(N + 1) { DEFAULT } }
    repeat(M) {
        val (u, v, b) = reader.readLine().split(" ").map { it.toInt() }

        graph[u][v] = 0
        graph[v][u] = if (b == 0) 1 else 0
    }
    for (i in 1..N) {
        graph[i][i] = 0
    }
    for (m in 1..N) {
        for (s in 1..N) {
            for (e in 1..N) {
                graph[s][e] = min(graph[s][e], graph[s][m] + graph[m][e])
            }
        }
    }
    val K = reader.readLine().toInt()
    repeat(K) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() }
        writer.write("${graph[u][v]}\n")
    }
    writer.flush()
}