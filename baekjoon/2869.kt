package `2869`

import java.util.*
import kotlin.math.ceil

fun main() {
    val scanner = Scanner(System.`in`)
    val A = scanner.nextInt()
    val B = scanner.nextInt()
    val V = scanner.nextInt() - A
    val result: Int = ceil(V.toDouble() / (A - B)).toInt() + 1
    println(result)
}