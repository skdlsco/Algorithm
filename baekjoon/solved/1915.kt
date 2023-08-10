package `1915`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val dp = Array<Array<Int>>(N + 1) { Array(M + 1) { 0 } }
    var ans = 0
    for (y in 1..N) {
        val iter = reader.readLine().iterator()
        for (x in 1..M) {
            if (iter.nextChar() == '0')
                dp[y][x] = 0
            else
                dp[y][x] = min(min(dp[y - 1][x], dp[y][x - 1]), dp[y - 1][x - 1]) + 1
            ans = max(dp[y][x] * dp[y][x], ans)
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}