package `9252`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val A = reader.readLine()
    val B = reader.readLine()
    val dp = Array<Array<Int>>(A.length + 1) { Array(B.length + 1) { 0 } }
    for (i in 1..A.length) {
        for (j in 1..B.length) {
            if (A[i - 1] == B[j - 1]) {
                // 두군데 다 이전 칸에서 와야한다.
                dp[i][j] = dp[i - 1][j - 1] + 1
            } else {
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }
    val lcs = StringBuilder()
    var i = A.length
    var j = B.length
    while (i > 0 && j > 0) {
        // dp 채우는 역순
        if (A[i - 1] == B[j - 1]) {
            lcs.append(A[i - 1])
            i--
            j--
        } else {
            if (dp[i][j] == dp[i - 1][j])
                i--
            else
                j--
        }
    }
    writer.write("${dp[A.length][B.length]}\n")
    writer.write("${lcs.reverse()}\n")
    writer.flush()
}