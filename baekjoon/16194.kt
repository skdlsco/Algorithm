package `16194`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val packs = reader.readLine().split(" ").map { it.toInt() }
    val dp = Array<Int>(N + 1) { 1000000000 }
    dp[0] = 0
    for (i in 1..N) {
        for (p in 0 until min(i, packs.size)) {
            dp[i] = min(dp[i - p - 1] + packs[p], dp[i])
        }
    }
    println(dp[N])
}