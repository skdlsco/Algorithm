package clear

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var n = 1
    val arr = Array<Boolean>(123456 * 2 + 1) { true }
    arr[0] = false
    arr[1] = false
    (2..123456).forEach {
        if (arr[it])
            ((it + it)..(123456 * 2) step it).forEach {
                arr[it] = false
            }
    }
    n = scanner.nextInt()
    while (n > 0) {
        val cnt = (n + 1..2 * n).count { arr[it] }
        println(cnt)
        n = scanner.nextInt()
    }
}