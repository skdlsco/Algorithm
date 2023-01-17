package clear

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val M = scanner.nextInt()
    var P = 0
    var money = 0
    var result = Pair(0, 0)
    val list = (0 until M).map {
        scanner.nextInt()
    }.sorted()
    list.forEach {
        P = it
        val cnt = list.filter { it >= P }.count()
        money = P * if (cnt > N) N else cnt
        if (money > result.second)
            result = Pair(P, money)
    }
    println("${result.first} ${result.second}")
}