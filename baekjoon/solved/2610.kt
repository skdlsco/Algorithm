package `2610`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val M = reader.readLine().toInt()
    val floydWarshall = Array<Array<Int>>(N + 1) { Array(N + 1) { -1 } }

    repeat(M) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        floydWarshall[a][b] = 1
        floydWarshall[b][a] = 1
    }
    for (i in 1..N) {
        floydWarshall[i][i] = 0
    }
    for (m in 1..N) {
        for (s in 1..N) {
            for (e in 1..N) {
                if (floydWarshall[s][m] < 0 || floydWarshall[m][e] < 0)
                    continue
                if (floydWarshall[s][e] == -1)
                    floydWarshall[s][e] = floydWarshall[s][m] + floydWarshall[m][e]
                floydWarshall[s][e] = minOf(floydWarshall[s][e], floydWarshall[s][m] + floydWarshall[m][e])
            }
        }
    }
    val visited = Array<Boolean>(N + 1) { false }
    val group = ArrayList<Int>()
    val members = ArrayList<Pair<Int, Int>>()
    for (i in 1..N) {
        if (visited[i])
            continue
        members.clear()
        for (j in 1..N) {
            if (floydWarshall[i][j] != -1) {
                visited[j] = true
                val max = (1..N).maxOf { floydWarshall[j][it] }
                members.add(Pair(j, max))
            }
        }
        val leader = members.minByOrNull { it.second }!!.first
        group.add(leader)
    }
    group.sortBy { it }
    writer.write("${group.size}\n")
    writer.write(group.joinToString("\n") { "$it" })
    writer.flush()
}