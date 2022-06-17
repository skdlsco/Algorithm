package clear

import java.lang.Integer.max
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    while (true) {
        val a = scanner.nextInt()
        val b = scanner.nextInt()
        val c = scanner.nextInt()
        if (a + b + c <= 0)
            break
        val hypotenuse= max(a, max(b, c))
        if (a * a + b * b + c * c == 2 * hypotenuse * hypotenuse)
            println("right")
        else
            println("wrong")
    }
}