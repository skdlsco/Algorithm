package `14728`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.`out`))

    val (N, T) = reader.readLine().split(" ").map { it.toInt() }
    val dp = Array<Int>(T + 1) { 0 }
    repeat(N) {
        val (K, S) = reader.readLine().split(" ").map { it.toInt() }
        (T downTo K).forEach { i ->
            dp[i] = max(dp[i], dp[i - K] + S)
        }
    }
    println(dp[T])
}