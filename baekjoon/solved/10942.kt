package `10942`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

class TokenReader(private val reader: BufferedReader) {
    private var tokenizer = StringTokenizer(reader.readLine())

    fun nextToken(): String {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = StringTokenizer(reader.readLine())
        }
        return tokenizer.nextToken()
    }

    fun nextInt(): Int {
        return nextToken().toInt()
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val tReader = TokenReader(reader)
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = tReader.nextInt()
    val arr = Array<Int>(N + 1) { 0 }
    val dp = Array<Array<Boolean>>(N + 1) { Array(N + 1) { false } }
    for (i in 1..N) {
        arr[i] = tReader.nextInt()
        dp[i][i] = true
        dp[i - 1][i] = arr[i] == arr[i - 1]
    }
    // 길이
    for (i in 3..N) {
        // 시작 idx
        for (j in 1..N - i + 1) {
            dp[j][j + i - 1] = dp[j + 1][j + i - 2] && arr[j] == arr[j + i - 1]
        }
    }
    val M = tReader.nextInt()
    repeat(M) {
        val s = tReader.nextInt()
        val e = tReader.nextInt()
        writer.write(if (dp[s][e]) "1" else "0")
        writer.newLine()
    }
    writer.flush()
}