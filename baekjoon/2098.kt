import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

fun solve(N: Int, graph: Array<Array<Int>>, dp: Array<Array<Int>>, bit: Int, cur: Int): Int {
    if (dp[cur][bit] != -1)
        return dp[cur][bit]
    if (bit == (1 shl N) - 1) {
        if (graph[cur][0] == 0)
            return Int.MAX_VALUE
        return graph[cur][0]
    }
    dp[cur][bit] = Int.MAX_VALUE
    for (i in 0 until N) {
        if (bit and (1 shl i) > 0 || graph[cur][i] == 0)
            continue
        val nextBit = bit or (1 shl i)
        val ans = solve(N, graph, dp, nextBit, i)
        if (ans == Int.MAX_VALUE)
            continue
        dp[cur][bit] = minOf(dp[cur][bit], ans + graph[cur][i])
    }
    return dp[cur][bit]
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val graph = Array<Array<Int>>(N) { Array(N) { 0 } }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N) {
            graph[y][x] = row[x]
        }
    }
    val dp = Array<Array<Int>>(N) { Array(1 shl N) { -1 } }
    solve(N, graph, dp, 1, 0)
    writer.write("${dp[0][1]}\n")
    writer.flush()
}