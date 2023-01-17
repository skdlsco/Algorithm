package c1931

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val arr = (0 until N).map { Pair<Int, Int>(scanner.nextInt(), scanner.nextInt()) }
        .sortedWith(compareBy<Pair<Int, Int>>({ it.second }, { it.first }))
    var cnt = 0
    var end = 0
    arr.forEach {
        if (it.second >= end && it.first >= end) {
            cnt++
            end = it.second
        }
    }
    println(cnt)
}