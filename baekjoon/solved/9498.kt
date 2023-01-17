package clear

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()

    val c = when {
        n >= 90 -> "A"
        n >= 80 -> "B"
        n >= 70 -> "C"
        n >= 60 -> "D"
        else -> "F"
    }
    println(c)
}