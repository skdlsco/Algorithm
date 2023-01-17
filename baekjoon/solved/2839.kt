package clear

import java.util.*
import kotlin.math.min

fun main() {
    val scanner = Scanner(System.`in`)
    var N = scanner.nextInt()
    var memo = IntArray(5001) { Int.MAX_VALUE }
    memo[3] = 1
    memo[5] = 1
    (5..5000).forEach {
        if (memo[it - 3] != Int.MAX_VALUE)
            memo[it] = memo[it - 3] + 1
        if (memo[it - 5] != Int.MAX_VALUE)
            memo[it] = min(memo[it], memo[it - 5] + 1)
    }
    if (memo[N] == Int.MAX_VALUE)
        println(-1)
    else
        println(memo[N])
}