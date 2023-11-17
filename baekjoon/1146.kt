package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

const val MOD = 1_000_000

fun solve(dp: Array<Array<Array<Int>>>, status: Int, big: Int, small: Int): Int {
    if (dp[status][big][small] != 0)
        return dp[status][big][small]
    var ans = 0
    // status: 0 big, 1 small
    if (status == 0) {
        for (i in 0 until big) {
            ans += solve(dp, 1, i, small + big - i - 1)
            ans %= MOD
        }
    } else {
        for (i in 0 until small) {
            ans += solve(dp, 0, big + small - i - 1, i)
            ans %= MOD
        }
    }
    dp[status][big][small] = ans
    return ans
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    if (N == 1) {
        writer.write("1\n")
        writer.flush()
        return
    }
    val dp = Array<Array<Array<Int>>>(2) { Array(N + 1) { Array(N + 1) { 0 } } }
    dp[0][0][0] = 1
    dp[1][0][0] = 1
    var ans = 0
    for (i in 0 until N) {
        ans += solve(dp, 0, i, N - i - 1)
        ans += solve(dp, 1, i, N - i - 1)
        ans %= MOD
    }
    writer.write("${ans}\n")
    writer.flush()
}
    