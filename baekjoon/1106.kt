package `1106`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (C, N) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Pair<Int, Int>>(N) { Pair(0, 0) }
    repeat(N) {
        val (cost, customer) = reader.readLine().split(" ").map { it.toInt() }
        arr[it] = Pair(cost, customer)

    }
    val dp = Array<Int>(C + 101) { Int.MAX_VALUE }
    dp[0] = 0
    for ((cost, num) in arr) {
        for (i in 0 until dp.size - num) {
            if (dp[i] != Int.MAX_VALUE)
                dp[i + num] = min(dp[i + num], dp[i] + cost)
        }
    }
    var ans = dp[C]
    for (i in C until dp.size) {
        ans = min(ans, dp[i])
    }
    writer.write("${ans}\n")
    writer.flush()
}
    