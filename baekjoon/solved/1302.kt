package `1302`

import java.util.*
import kotlin.collections.HashMap

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val map = HashMap<String, Int>()

    repeat(N) {
        val book = scanner.next()
        if (map.containsKey(book))
            map[book] = map[book]!! + 1
        else
            map[book] = 1
    }
    val result = map.toSortedMap().maxByOrNull { it.value }!!
    println(result.key)
}