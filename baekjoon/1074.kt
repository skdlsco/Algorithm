import java.util.*
import kotlin.math.pow

fun find(N: Int, r: Int, c: Int): Int {
    if (N == 1)
        return r * 2 + c * 1
    val half = 2.0.pow(N - 1).toInt()
    val nextR = if (r >= half) r - half else r
    val nextC = if (c >= half) c - half else c
    val cell = (if (r != nextR) 2 else 0) + (if (c != nextC) 1 else 0)
    return half * half * cell + find(N - 1, nextR, nextC)
}

fun main() {
    val scanner = Scanner(System.`in`)
    val (N, r, c) = scanner.nextLine().split(" ").map { it.toInt() }

    println(find(N, r, c))
}