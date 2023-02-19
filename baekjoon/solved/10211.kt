package `10211`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val N = reader.readLine().toInt()
        val stringTokenizer = StringTokenizer(reader.readLine())
        val arr = Array<Int>(N + 1) { 0 }
        val dp = Array<Int>(N + 1) { 0 }
        for (i in 1..N) {
            arr[i] = stringTokenizer.nextToken().toInt()
        }
        var result = Int.MIN_VALUE
        for (i in 1..N) {
            dp[i] = arr[i]
            if (arr[i] + dp[i - 1] > dp[i])
                dp[i] = arr[i] + dp[i - 1]
            result = max(result, dp[i])
        }
        writer.write("${result}\n")
    }
    writer.flush()
}