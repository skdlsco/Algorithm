package `1676`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    var cnt = 0
    repeat(N + 1) {
        var i = it
        while (i != 0 && i % 5 == 0) {
            i /= 5
            cnt++
        }
    }
    println(cnt)
}