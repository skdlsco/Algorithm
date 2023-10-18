package `9251`
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val a = reader.readLine()
    val b = reader.readLine()
    val dp = Array<IntArray>(a.length + 1) { IntArray(b.length + 1) { 0 } }

    (a.indices).forEach { y ->
        (b.indices).forEach { x ->
            if (a[y] == b[x])
                dp[y + 1][x + 1] = dp[y][x] + 1
            else
                dp[y + 1][x + 1] = max(dp[y][x + 1], dp[y + 1][x])
        }
    }
    println(dp[a.length][b.length])
}