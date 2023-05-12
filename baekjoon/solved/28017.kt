package `28017`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val dp = Array<Array<Int>>(2) { Array(M) { 0 } }
    for (it in 1..N) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        for (i in 0 until M) {
            val cur = stringTokenizer.nextToken().toInt()
            dp[it % 2][i] = Int.MAX_VALUE
            for (j in 0 until M) {
                if (i != j) {
                    dp[it % 2][i] = min(dp[(it - 1) % 2][j] + cur, dp[it % 2][i])
                }
            }
        }
    }
    writer.write("${dp[N % 2].minOf { it }}\n")
    writer.flush()
}