import java.util.*
import kotlin.math.min

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val dp = Array<Int>(n + 1) { Int.MAX_VALUE }
    dp[0] = 0
    (1..n).forEach {
        var i = 1
        while (i * i <= it) {
            dp[it] = min(dp[it], dp[it - (i * i)] + 1)
            i++
        }
    }
    println(dp[n])
}