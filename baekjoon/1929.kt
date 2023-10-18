package `1929`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val arr = Array<Boolean>(1000001) { true }
    val M = scanner.nextInt()
    val N = scanner.nextInt()

    arr[0] = false
    arr[1] = false
    (2..1000).forEach {
        if (arr[it]) {
            ((it + it)..1000000 step it).forEach {
                arr[it] = false
            }
        }
    }
    (M..N).forEach {
        if (arr[it])
            println(it)
    }
}