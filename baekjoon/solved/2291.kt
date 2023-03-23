package `2291`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    // dp[i][j][k], i자리 수열 합 j, k가 끝자리
    val dp = Array<Array<Array<Long>>>(N + 1) { Array(M + 1) { Array(M + 1) { 0L } } }
    for (num in 0..M) {
        dp[1][num][num] = 1L
    }
    for (i in 2..N) {
        for (j in i..M) {
            for (k in 1..j) {
                for (a in k..M) {
                    dp[i][j][k] += dp[i - 1][j - k][a]
                }
            }
        }
    }
    val result = ArrayList<Int>()
    var target = K
    var prevSum = M
    var prevNum = 1
    for (i in N downTo 1) {
        var sum = 0L
        for (j in prevNum .. M) {
            sum += dp[i][prevSum][j]
            if (sum >= target) {
                result.add(j)
                target -= (sum - dp[i][prevSum][j]).toInt()
                prevSum -= j
                prevNum = j
                break
            }
        }
    }
    writer.write(result.joinToString(" "))
    writer.flush()
}