package `10871`

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val x = scanner.nextInt()
    (1..n).forEach{
        val a = scanner.nextInt()
        if (a < x)
            print("$a ")
    }
}