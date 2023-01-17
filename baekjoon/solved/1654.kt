import java.util.*
import kotlin.math.max

fun main() {
    val scanner = Scanner(System.`in`)
    val (K, N) = scanner.nextLine().split(" ").map { it.toInt() }
    val arr = (0 until K).map { scanner.nextLong() }
    var left = 1L
    var right = arr.maxOf { it }
    var result = 1L
    while (left <= right) {
        val mid = (left + right) / 2
        if (arr.sumOf { it / mid } < N) {
            right = mid - 1
        } else {
            result = max(result, mid)
            left = mid + 1
        }
    }
    println(result)
}