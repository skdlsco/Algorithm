package `15486`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val dp = Array<Int>(N + 51) { 0 }
    repeat(N) { s ->
        val (T, P) = reader.readLine().split(" ").map { it.toInt() }
        dp[s + T] = max(dp[s + T], dp[s] + P)
        dp[s + 1] = max(dp[s], dp[s + 1])
    }
    writer.write("${dp.slice(0..N).maxOf { it }}\n")
    writer.flush()
}