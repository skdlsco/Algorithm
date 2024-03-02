package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun dfs(vtx: Array<ArrayList<Int>>, visited: Array<Boolean>, barn: Array<Int>, cur: Int): Boolean {
    visited[cur] = true
    for (next in vtx[cur]) {
        if (barn[next] == -1 || !visited[barn[next]] && dfs(vtx, visited, barn, barn[next])) {
            barn[next] = cur
            return true
        }
    }
    return false
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val cow = Array<ArrayList<Int>>(N * 2) { ArrayList() }
    for (i in 0 until N) {
        val st = StringTokenizer(reader.readLine())
        val cnt = st.nextToken().toInt()
        repeat(cnt) {
            cow[i * 2].add(st.nextToken().toInt() - 1)
        }
        cow[i * 2 + 1] = cow[i * 2]
    }
    val barn = Array<Int>(M) { -1 }
    val visited = Array<Boolean>(N * 2) { false }
    var ans = 0
    for (i in 0 until N * 2) {
        visited.fill(false, 0, N * 2)
        if (dfs(cow, visited, barn, i))
            ans++
    }
    writer.write("${ans}\n")
    writer.flush()
}