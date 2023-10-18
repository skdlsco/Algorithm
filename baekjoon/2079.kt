package `2079`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val S = " " + reader.readLine()
    // i에서 j까지 팰린드롬인가?
    val palinCheck = Array<Array<Boolean>>(S.length) { Array(S.length) { false } }
    // 간격
    for (distance in 0 until S.length) {
        for (s in 0 until S.length - distance) {
            if (distance == 0) {
                palinCheck[s][s + distance] = true
            } else if (distance == 1) {
                palinCheck[s][s + distance] = S[s] == S[s + distance]
            } else {
                palinCheck[s][s + distance] = palinCheck[s + 1][s + distance - 2 + 1] && S[s] == S[s + distance]
            }
        }
    }
    val dp = Array<Int>(S.length) { Int.MAX_VALUE }
    dp[0] = 0
    for (i in 1 until S.length) {
        for (j in i until S.length) {
            if (palinCheck[i][j])
                dp[j] = minOf(dp[j], dp[i - 1] + 1)
        }
    }
    writer.write("${dp.last()}\n")
    writer.flush()
}
    