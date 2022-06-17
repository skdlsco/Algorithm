package clear

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val T = scanner.nextInt()
    val result = Array<Array<Int>>(15) { Array<Int>(15) { it } }
    (1..14).forEach { k ->
        (2..14).forEach { n ->
            result[k][n] = result[k][n - 1] + result[k - 1][n]
        }
    }
    repeat(T) {
        val k = scanner.nextInt()
        val n = scanner.nextInt()
        println(result[k][n])
    }
}
