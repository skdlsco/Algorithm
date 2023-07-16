package `1509`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val str = " " + reader.readLine()
    // dp[i] = i의 분할의 개수 최솟값
    val dp = Array<Int>(str.length) { Int.MAX_VALUE }
    // check[i][j] => i..j가 팰린드롬인가?
    val check = Array<Array<Boolean>>(str.length) { Array(str.length) { false } }
    for (i in str.indices) {
        check[i][i] = true
    }
    dp[0] = 0
    for (e in 1 until str.length) {
        dp[e] = dp[e - 1] + 1
        for (s in e - 1 downTo 1) {
            if (e - s < 2)
                check[s][e] = str[s] == str[e]
            else
                check[s][e] = check[s + 1][e - 1] && str[s] == str[e]
            if (check[s][e])
                dp[e] = min(dp[e], dp[s - 1] + 1)
        }
    }
    writer.write("${dp.last()}\n")
    writer.flush()
}