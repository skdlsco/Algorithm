package `11720`

import java.util.*

fun main(args: Array<String>) {

    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val s = scanner.next()
    var sum = 0
    (0 until n).forEach{
        val a = Integer.parseInt(s[it].toString())
        sum += a
    }
    print(sum)
}
