package `G`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Long>(N + 1) { 0L }
    val input = StringTokenizer(reader.readLine())
    for (i in 1..N) {
        arr[i] = input.nextToken().toLong()
    }
    val dp = Array<Long>(N + 1) { Long.MIN_VALUE }
    dp[0] = 0L
    dp[1] = arr[1]
    dp[2] = arr[2] + arr[1]
    for (i in 3..N) {
        dp[i] = maxOf(dp[i - 1] + arr[i],
                dp[i - 3] + (arr[i - 2] + arr[i - 1] + arr[i]) * 2)
    }
    dp[N] = maxOf(dp[N],
            dp[N - 1] + arr[N] * 2,
            dp[N - 2] + (arr[N - 1] + arr[N]) * 2)
    writer.write("${dp[N]}\n")
    writer.flush()
}
    