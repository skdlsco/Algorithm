package `Main`

import kotlin.math.max

fun main() {
    val reader = System.`in`.bufferedReader()
    val N = reader.readLine().toInt()
    val dp = Array<Int>(N + 1) { 0 }

    for (i in 1..N) {
        val check = BooleanArray(N + 1)
        for (j in 0 until i) {
            val left = max(0, j - 2)
            val right = max(i - (j + 2) - 1, 0)
            check[dp[left] xor dp[right]] = true
        }
        dp[i] = check.indexOfFirst { !it }
    }
    println(if (dp[N] == 0) 2 else 1)
}