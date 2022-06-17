import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val input = reader.readLine().split(" ").map { it.toInt() }
    val dp = Array<Int>(N) { 0 }
    val arr = Array<Int>(N) { Int.MAX_VALUE }

    dp[0] = 1
    arr[0] = input[0]
    (1 until N).forEach {
        var target = 0
        run loop@{
            (0 until N).forEach { i ->
                if (arr[i] >= input[it]) {
                    target = i
                    return@loop
                }
            }
        }
        if (target != N)
            arr[target] = input[it]
        dp[it] = max(dp[it - 1], target + 1)
    }
    println(dp[N - 1])
}