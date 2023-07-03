package `2629`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val dp = Array<Boolean>(15001) { false }
    dp[0] = true
    for (i in arr) {
        for (j in 15000 downTo 0) {
            if (dp[j] && j + i < 15001)
                dp[j + i] = true
        }
    }
    for (i in arr) {
        for (j in i..15000) {
            if (dp[j]) {
                dp[j - i] = true
            }
        }
    }
    val T = reader.readLine().toInt()
    val ans = reader.readLine()
        .split(" ")
        .map { it.toInt() }.joinToString(" ") { if (it < 15001 && dp[it]) "Y" else "N" }
    writer.write(ans)
    writer.flush()
}