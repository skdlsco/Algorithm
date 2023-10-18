import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun dfs(N: Int, graph: Array<ArrayList<Int>>, dp: Array<Array<Int>>, k: Int, cur: Int) {
    val cnt = graph[cur].size
    dp[cur][k] = graph[cur].sumOf { dp[it][k - 1] } - (cnt - 1) * dp[cur][k - 2]
    for (next in graph[cur]) {
        if (dp[next][k] == 0)
            dfs(N, graph, dp, k, next)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Int>>(N + 1) { ArrayList() }
    val dp = Array<Array<Int>>(N + 1) { Array(K + 1) { 0 } }
    repeat(N - 1) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }

    for (i in 1..N) {
        dp[i][0] = 1
        dp[i][1] = graph[i].size + 1
    }
    for (k in 2..K) {
        dfs(N, graph, dp, k, 1)
    }
    writer.write("${(1..N).maxOf { dp[it][K] }}\n")
    writer.flush()
}
