package `17427`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.`out`))
    val T = reader.readLine().toInt()
    val dp = LongArray(1000001) { 0L }
    // 1 ~ 1000000까지 i의 배수에 i를 더한다.
    // n 까지의 합을 구해야 하므로 dp[i] += dp[i - 1]
    for (i in 1..1000000) {
        val max = 1000000 / i
        for (j in 1..max) {
            dp[i * j] += i.toLong()
        }
        dp[i] += dp[i - 1]
    }
    repeat(T) {
        val N = reader.readLine().toInt()
        writer.write("${dp[N]}\n")
    }
    writer.flush()
    writer.close()
}
