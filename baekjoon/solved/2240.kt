package `2240`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

val dp = Array<Array<Array<Int>>>(1001) { Array(31) { Array(2) { 0 } } }
val arr = Array<Int>(1001) { 0 }

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (T, W) = reader.readLine().split(" ").map { it.toInt() }
    repeat(T) {
        arr[it + 1] = reader.readLine().toInt()
    }
    for (i in 1..T) {
        dp[i][0][0] = dp[i - 1][0][0]
        if (arr[i] == 1)
            dp[i][0][0]++
    }
    for (j in 1..T) {
        for (i in 1..W) {
            dp[j][i][0] = max(dp[j - 1][i - 1][1], dp[j - 1][i][0])
            dp[j][i][1] = max(dp[j - 1][i - 1][0], dp[j - 1][i][1])
            if (arr[j] == 1)
                dp[j][i][0]++
            else
                dp[j][i][1]++
        }
    }
    writer.write("${dp[T].slice(0..W).maxOf { it.maxOf { it } }}\n")
    writer.flush()
}
    