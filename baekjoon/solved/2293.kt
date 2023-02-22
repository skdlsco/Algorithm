package `2293`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (n, k) = reader.readLine().split(" ").map { it.toInt() }
    val coinArr = IntArray(n + 1)
    val dp = Array<IntArray>(2) { IntArray(k + 1) }

    dp[0][0] = 1
    repeat(n) {
        coinArr[it + 1] = reader.readLine().toInt()
    }

    for (i in 1..n) {
        for (j in 0..k) {
            // 동전을 몇개 사용해서 k가 되었는가?
            dp[i % 2][j] = dp[(i - 1) % 2][j]
            if (j - coinArr[i] >= 0)
                dp[i % 2][j] += dp[i % 2][j - coinArr[i]]
        }
    }
    println(dp[n % 2][k])
}