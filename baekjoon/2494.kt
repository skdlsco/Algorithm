package `2494`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

const val MAX_VALUE = 100000

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    // 왼쪽 회전 수
    // dp[i][j]: i번째 숫자나사가 j만큼 돌아간 상태 일 때 target[i]가 되도록 돌린 횟수의 최소값
    val dp = Array<Array<Int>>(10001) { Array(10) { MAX_VALUE } }
    val path = Array<Array<Int>>(10001) { Array(10) { 0 } }
    val N = reader.readLine().toInt()
    val start = arrayOf(0).plus(reader.readLine().map { it.digitToInt() })
    val target = arrayOf(0).plus(reader.readLine().map { it.digitToInt() })
    dp[0][0] = 0
    for (i in 1..N) {
        for (j in 0 until 10) {
            // 왼쪽, 오른쪽, 가만히 중에서 start == target이 되는 경우 찾기
            if ((start[i] + j) % 10 == target[i]) {
                if (dp[i][j] > dp[i - 1][j]) {
                    dp[i][j] = dp[i - 1][j]
                    path[i][j] = 0
                }
            } else {
                val cur = (start[i] + j) % 10
                // 왼쪽으로 가기
                val left = if (target[i] > cur) target[i] - cur
                else 10 - (cur - target[i])
                val right = 10 - left
                if (dp[i][(j + left) % 10] > dp[i - 1][j] + left) {
                    dp[i][(j + left) % 10] = dp[i - 1][j] + left
                    path[i][(j + left) % 10] = left
                }
                if (dp[i][j] > dp[i - 1][j] + right) {
                    dp[i][j] = dp[i - 1][j] + right
                    path[i][j] = -right
                }
            }
        }
    }
    val result = dp[N].minOf { it }
    var cur = dp[N].indexOf(result)
    writer.write("$result\n")
    for (i in N downTo 1) {
        writer.write("$i ${path[i][cur]}\n")
        if (path[i][cur] > 0)
            cur += 10 - path[i][cur]
        cur %= 10
    }
    writer.flush()
}