package clear

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    var n2 = n
    var count = 0
    do {
        n2 = (((n2 / 10) + (n2 % 10)) % 10) + (n2 % 10) * 10
        count++
    } while (n != n2)
    println(count)
}