package `Main`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val dp = Array<Int>(N + 1) { 0 }

    for (i in 2..N) {
        val check = BooleanArray(i + 1)
        for (j in 0..i - 2) {
            check[dp[j] xor dp[i - j - 2]] = true
        }
        dp[i] = check.indexOfFirst { !it }
    }
    if (dp[N] == 0)
        println(2)
    else
        println(1)
}