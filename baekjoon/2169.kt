package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Array<Int>>(N) { Array(M) { 0 } }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until M) {
            arr[y][x] = row[x]
        }
    }
    val dp = Array<Array<Int>>(N) { Array(M) { Int.MIN_VALUE } }
    dp[0][0] = arr[0][0]
    for (y in 0 until N) {
        // 오른쪽
        for (x in 0 until M) {
            if (x > 0)
                dp[y][x] = maxOf(dp[y][x], dp[y][x - 1] + arr[y][x])
            if (y > 0)
                dp[y][x] = maxOf(dp[y][x], dp[y - 1][x] + arr[y][x])
        }
        if (y == 0)
            continue
        // 왼쪽
        var left = dp[y - 1][M - 1]
        for (x in M - 1 downTo 0) {
            left += arr[y][x]
            if (x < M - 1)
                left = maxOf(left, dp[y - 1][x] + arr[y][x])
            dp[y][x] = maxOf(dp[y][x], left)
        }
    }
    writer.write("${dp[N - 1][M - 1]}\n")
    writer.flush()
}
    