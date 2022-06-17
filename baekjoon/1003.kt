package clear

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val T = scanner.nextInt()

    val array = Array<Pair<Int, Int>>(1000) { Pair(1, 0) }
    array[1] = Pair(0, 1)

    (2 until 1000).forEach {
        val first = array[it - 1].first + array[it - 2].first
        val second = array[it - 1].second + array[it - 2].second
        array[it] = Pair(first, second)
    }

    repeat(T) {
        val N = scanner.nextInt()

        println("${array[N].first} ${array[N].second}")
    }
}