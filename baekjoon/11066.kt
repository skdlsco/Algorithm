package `11066`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val T = reader.readLine().toInt()
    repeat(T) {
        val N = reader.readLine().toInt()
        val arr = reader.readLine().split(" ").map { it.toInt() }
        val sumArr = IntArray(N)
        sumArr[0] = arr[0]
        val dp = Array<Array<Int>>(N) { Array(N) { 0 } }
        // i 부터 j 까지 처리했을 때의 최소값

        for (i in 1 until N) {
            sumArr[i] = sumArr[i - 1] + arr[i]
        }
        // 간격
        for (len in 1 until N) {
            for (start in 0 until N - len) {
                val end = start + len
                dp[start][end] = Int.MAX_VALUE
                // i 와 j 사이 지점을 선택
                for (mid in start until end) {
                    dp[start][end] =
                        min(dp[start][end], dp[start][mid] + dp[mid + 1][end] + sumArr[end] - sumArr[start] + arr[start])
                }
            }
        }
        writer.write("${dp[0][N - 1]}\n")
    }
    writer.flush()
}