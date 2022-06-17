package clear

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var N = scanner.nextInt()
    var i = 2
    
    while (N > 1) {
        if (N % i == 0) {
            N /= i
            println(i)
        } else
            i++
    }
}