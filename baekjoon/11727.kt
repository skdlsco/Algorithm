package `11727`
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val dp = Array<Int>(N + 1) { 0 }
    dp[0] = 1
    dp[1] = 1
    (2..N).forEach {
        dp[it] = (dp[it - 2] + dp[it - 1] + dp[it - 2]) % 10007
    }
    println(dp[N])
}