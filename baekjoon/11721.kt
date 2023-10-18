package `11721`

import java.util.*

fun main(args: Array<String>) {

    val scanner = Scanner(System.`in`)
    val s = scanner.next()
    s.forEachIndexed { index, c ->
        print(c)
        if((index + 1) % 10 == 0)
            println()
    }
}
