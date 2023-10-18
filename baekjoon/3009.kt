package `3009`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val input = Array<Pair<Int, Int>>(3) { Pair(scanner.nextInt(), scanner.nextInt()) }

    input.forEachIndexed dot1@{ idx1, pair1 ->
        val another = input.filterIndexed { index, pair -> idx1 != index }
        var cnt = 0
        var dot = Pair<Int, Int>(0, 0)
        another.forEach {
            if (pair1.first == it.first) {
                cnt++
                dot = Pair(dot.first, it.second)
            }
            else if (pair1.second == it.second) {
                cnt++
                dot = Pair(it.first, dot.second)
            }
        }
        if (cnt == 2) {
            println("${dot.first} ${dot.second}")
            return
        }
    }
}