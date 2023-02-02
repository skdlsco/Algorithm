package `1546`

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val arr = arrayListOf<Int>()
    var max = 0
    repeat(N) {
        val score = scanner.nextInt()
        if (score > max)
            max = score
        arr.add(score)
    }
    val sum = arr.map {
        it * 100 / max.toDouble()
    }.sum()
    val ave = sum / N
    println(ave)
}