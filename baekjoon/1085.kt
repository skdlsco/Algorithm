package clear

import java.util.*
import kotlin.math.min

fun main() {
    val scanner = Scanner(System.`in`)
    val x = scanner.nextInt()
    val y = scanner.nextInt()
    val w = scanner.nextInt()
    val h = scanner.nextInt()

    var result = Int.MAX_VALUE
    result = min(result, x)
    result = min(result, w - x)
    result = min(result, y)
    result = min(result, h - y)
    println(result)
}