package `1011`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val T = scanner.nextInt()

    repeat(T) {
        val x = scanner.nextInt()
        val y = scanner.nextInt()
        var target = y - x
        var move = 0
        var num = 0
        var sum = 0

        while (target > 0) {
            if (target - (sum + num + 1) >= 0) {
                num++
                sum += num
            } else if (target - sum < 0) {
                sum -= num
                num--
            }
            target -= num
            move++
        }
        println(move)
    }
}