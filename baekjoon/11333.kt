package `11333`

import java.io.BufferedReader
import java.io.InputStreamReader

val MOD = 1000000007
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val T = reader.readLine().toInt()

    val dp = Array<Int>(10001) { 0 }
    dp[0] = 1
    dp[1] = 2
    dp[2] = 2
    for (i in 3..10000) {
        if (i % 3 == 1) {
            dp[i] = (((dp[i - 1] * 2) % MOD) + dp[i - 3]) % MOD
        } else {
            dp[i] = (dp[i - 1] + dp[i - 3]) % MOD
        }
    }

    repeat(T) {
        val N = reader.readLine().toInt()
//        if (N % 3 == 0)
        println(dp[N])
//        else
//            println(0)
    }
}