package `11049`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

data class Data(val row: Int, val column: Int) {
    operator fun plus(other: Data): Int {
        return this.row * this.column * other.column
    }
}

// note
//  input 순서를 변경하지 않고 앞뒤로만 확장시킬때? 합칠 때?
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val input = Array<Data>(N) { Data(0, 0) }
    // dp[i][j] -> i부터 j까지 합쳤을 때의 최솟값
    val dp = Array<Array<Int>>(N) { Array(N) { Int.MAX_VALUE } }
    repeat(N) {
        val (row, col) = reader.readLine().split(" ").map { it.toInt() }
        input[it] = Data(row, col)
        dp[it][it] = 0
    }
    for (d in 1 until N) {
        for (s in 0 until N - d) {
            val e = s + d
            for (m in s until e) {
                val count = input[s].row * input[m].column * input[e].column
                dp[s][e] = min(dp[s][e], dp[s][m] + dp[m + 1][e] + count)
            }
        }
    }
    writer.write("${dp[0][N - 1]}\n")
    writer.flush()
}
