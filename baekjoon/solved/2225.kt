package `2225`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val dp = Array<Array<Int>>(N + 1) { Array(K + 1) { 0 } }
    dp[0][0] = 1
    for (i in 1..K) {
        for (j in 0..N) {
            for (k in j..N) {
                dp[k][i] = (dp[k - j][i - 1] + dp[k][i]) % 1000000000
            }
        }
    }
    writer.write("${dp[N][K]}\n")
    writer.flush()
}