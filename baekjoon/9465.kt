package `9465`
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val T = reader.readLine().toInt()
    repeat(T) {
        val N = reader.readLine().toInt()
        val input = Array<Array<Int>>(2) { reader.readLine().split(" ").map { it.toInt() }.toTypedArray() }
        val dp = Array<Array<Int>>(2) { Array(N) { 0 } }
        dp[0][0] = input[0][0]
        dp[1][0] = input[1][0]

        if (N > 1){
            dp[0][1] = input[0][1] + dp[1][0]
            dp[1][1] = input[1][1] + dp[0][0]
            (2 until N).forEach {
                dp[0][it] = max(dp[1][it - 1], dp[1][it - 2]) + input[0][it]
                dp[1][it] = max(dp[0][it - 1], dp[0][it - 2]) + input[1][it]
            }
        }
        println(max(dp[0][N - 1], dp[1][N - 1]))
    }
}