package `2248`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val stringTokenizer = StringTokenizer(reader.readLine())
    var N = stringTokenizer.nextToken().toInt()
    var L = stringTokenizer.nextToken().toInt()
    var I = stringTokenizer.nextToken().toUInt()
    // N자리 L개
    val dp = Array<Array<UInt>>(N + 1) { Array(N + 1) { 0U } }
    dp[0][0] = 1U
    for (i in 1..N) {
        for (j in 0..i) {
            dp[i][j] = dp[i - 1][j]
            if (j > 0)
                dp[i][j] += dp[i - 1][j - 1]
        }
    }
    for (i in 1..N) {
        for (j in 1..N) {
            dp[i][j] += dp[i][j - 1]
        }
    }
    var result = ""
    while (I > 1U) {
        var i = 0
        while (dp[i][L] < I)
            i++
        repeat(N - i) {
            result += "0"
        }
        if (dp[i][min(L, i)] == I) {
            repeat(min(L, i)) {
                result += "1"
            }
            repeat(i - L) {
                result += "0"
            }
            break
        }
        result += "1"
        N -= (N - i + 1)
        I -= dp[i - 1][L]
        L -= 1
    }
    if (I == 1U) {
        repeat(N) {
            result += "0"
        }
    }
    println(result)
}