package `2675`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val case = scanner.nextInt()
    repeat(case) {
        val repeat = scanner.nextInt()
        val s = scanner.next()
        val result = s.map {
            (0 until repeat).map { _ -> it }.joinToString("") { it.toString() }
        }.joinToString("") { it }
        println(result)
    }
}