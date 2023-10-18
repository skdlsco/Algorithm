package `1865`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))
val INF = 123456789

data class Edge(val s: Int, val e: Int, val c: Int)

fun bellmanFord(edges: List<Edge>, N: Int, start: Int): Boolean {
    val table = Array<Int>(N + 1) { INF }
    table[start] = 0
    for (i in 0 until N) {
        for ((s, e, c) in edges) {
            //  사이클만 검증하기 때문에, 시작점이 어디든 될 수 있기 때문에
            //  INF 갈 수 있는지 체크 X
            if (table[e] > table[s] + c) {
                if (i == N - 1)
                    return true
                table[e] = table[s] + c
            }
        }
    }
    return false
}

fun solve(): Boolean {
    val (N, M, W) = reader.readLine().split(" ").map { it.toInt() }
    val edges = ArrayList<Edge>()
    val starts = ArrayList<Int>()
    repeat(M + W) {
        val (s, e, c) = reader.readLine().split(" ").map { it.toInt() }
        if (it < M) {
            edges.add(Edge(s, e, c))
            edges.add(Edge(e, s, c))
        } else {
            edges.add(Edge(s, e, -c))
            starts.add(s)
        }
    }
    return bellmanFord(edges, N, 1)
}

fun main() {
    val TC = reader.readLine().toInt()
    repeat(TC) {
        val result = solve()
        writer.write(if (result) "YES" else "NO")
        writer.newLine()
    }
    writer.flush()
}