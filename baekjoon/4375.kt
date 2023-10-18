package `4375`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    while(scanner.hasNextLine()) {
        val N = scanner.nextLine().toInt()
        var cnt = 1
        var tmp = 1
        while (tmp % N != 0) {
            tmp = (tmp * 10 + 1) % N
            cnt++
        }
        println(cnt)
    }
}