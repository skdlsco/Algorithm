package `1699`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val dp = Array<Int>(100001) { 100001 }
    dp[0] = 0

    for (i in 1..1000) {
        for (j in i * i..100000) {
            dp[j] = min(dp[j], dp[j - i * i] + 1)
        }
    }
    writer.write("${dp[N]}\n")
    writer.flush()
}