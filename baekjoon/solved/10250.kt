package `10250`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val T = scanner.nextLine().toInt()
    (0 until T).forEach {
        var (H, W, N) = scanner.nextLine().split(' ').map { it.toInt() }

        var floor = N % H
        var room = N / H + 1
        if (floor == 0) {
            floor = H
            room -= 1
        }
        println("$floor${if (room < 10) "0$room" else "$room"}")
    }
}
