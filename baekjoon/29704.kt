package `29704`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, T) = reader.readLine().split(" ").map { it.toInt() }
    var sum = 0
    val problem = Array<Pair<Int, Int>>(N) {
        val (d, m) = reader.readLine().split(" ").map { it.toInt() }
        sum += m
        Pair(d, m)
    }
    val dp = Array<Int>(T + 1) { 0 }
    for ((d, m) in problem) {
        for (i in T downTo d) {
            dp[i] = max(dp[i], dp[i - d] + m)
        }
    }
    val max = dp.maxOf { it }
    writer.write("${sum - max}\n")
    writer.flush()
}
    