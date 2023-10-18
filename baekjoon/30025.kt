package `30025`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val MOD = 1_000_000_007L
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val use = Array<Boolean>(10) { false }
    val arr = reader.readLine().split(" ").map { it.toInt() }
    for (a in arr) {
        use[a] = true
    }
    val dp = Array<Array<Array<Long>>>(M + 1) { Array(10) { Array(K) { 0 } } }
    for (i in 1..9) {
        if (use[i])
            dp[1][i][i % K] = 1
    }
    for (m in 1..M) {
        for (s in 0..9) {
            for (e in 0..9) {
                if (!use[e])
                    continue
                for (k in 0 until K) {
                    dp[m][e][(k * 10 + e) % K] += dp[m - 1][s][k]
                    dp[m][e][(k * 10 + e) % K] %= MOD
                }
            }
        }
    }
    var sum = 0L
    for (i in 0..9) {
        sum += dp[M][i][0]
        sum %= MOD
    }
    writer.write("${sum}\n")
    writer.flush()
}
    