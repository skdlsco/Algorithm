package `27114`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

val INIT = 123456789
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (A, B, C, K) = reader.readLine().split(" ").map { it.toInt() }

    val dp = Array<Array<Int>>(K + 1) { Array(4) { INIT } }
    dp[0][0] = 0
    for (i in 1..K) {
        for (d in 0 until 4) {
            if (i >= A) {
                dp[i][d] = min(dp[i][d], dp[i - A][(d + 1) % 4] + 1)
            }
            if (i >= B) {
                dp[i][d] = min(dp[i][d], dp[i - B][(d + 3) % 4] + 1)
            }
            if (i >= C) {
                dp[i][d] = min(dp[i][d], dp[i - C][(d + 2) % 4] + 1)
            }
        }
    }
    val ans = dp[K][0]
    if (ans == INIT)
        writer.write("${-1}\n")
    else
        writer.write("${ans}")
    writer.flush()
}
    