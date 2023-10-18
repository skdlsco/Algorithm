package `29756`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val sArr = Array<Int>(N + 1) { 0 }
    val hArr = Array<Int>(N + 1) { 0 }
    reader.readLine().split(" ").map { it.toInt() }.forEachIndexed { idx, i -> sArr[idx + 1] = i }
    reader.readLine().split(" ").map { it.toInt() }.forEachIndexed { idx, i -> hArr[idx + 1] = i }
    val dp = Array<Array<Int>>(N + 1) { Array(101) { 0 } }
    for (i in 1..N) {
        val s = sArr[i]
        val h = hArr[i]

        for (hp in 0..100) {
            dp[i][hp] = dp[i - 1][hp]
            if (hp >= h)
                dp[i][hp - h] = maxOf(dp[i][hp - h], dp[i - 1][hp] + s)
        }
        for (j in 100 downTo 0) {
            val hp = min(j + K, 100)
            dp[i][hp] = maxOf(dp[i][hp], dp[i][j])
        }
    }
    writer.write("${dp[N].maxOf { it }}\n")
    writer.flush()
}
    