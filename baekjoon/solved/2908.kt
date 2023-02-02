package `2908`

import java.util.*
import kotlin.math.max

fun main() {
    val scanner = Scanner(System.`in`)
    val num1 = scanner.next().reversed()
    val num2 = scanner.next().reversed()

    num1.reversed()
    num2.reversed()
    println(max(num1.toInt(), num2.toInt()))
}