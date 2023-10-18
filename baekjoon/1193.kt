package `1193`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()

    var sum = 0
    var i = 0
    while (sum < N) {
        i++
        sum += i
    }
    if (i % 2 == 0)
        println("${i - (sum - N)}/${sum - N + 1}")
    else
        println("${sum - N + 1}/${i - (sum - N)}")
}