package `1978`

import java.util.*
import kotlin.math.sqrt

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()

    var cnt = 0
    repeat(N) {
        val number = scanner.nextInt()

        val isPrime = (2..sqrt(number.toDouble()).toInt()).none {
           number % it == 0
        }
        if (isPrime && number > 1)
            cnt++
    }
    println(cnt)
}