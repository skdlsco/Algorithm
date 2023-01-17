package `2616`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val containerCount = reader.readLine().toInt()
    val containerValueList = reader.readLine().split(" ").map { it.toInt() }
    val capacity = reader.readLine().toInt()

    val dp = Array<Array<Int>>(3) { Array(containerCount) { 0 } }
    dp[0][capacity - 1] = containerValueList.slice(0 until capacity).sum()
    (capacity until containerCount).forEach {
        val nowValue = (0 until capacity).sumOf { i -> containerValueList[it - i] }
        dp[0][it] = max(nowValue, dp[0][it - 1])
        dp[1][it] = max(nowValue + dp[0][it - capacity], dp[1][it - 1])
        dp[2][it] = max(nowValue + dp[1][it - capacity], dp[2][it - 1])
    }
    writer.write("${dp[2].maxOf { it }}")
    writer.flush()
    writer.close()
    reader.close()
}

