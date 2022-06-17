package clear

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val arr = Array<ArrayList<Int>>(N + 1) { ArrayList() }
    var sum = 0
    repeat(N) {
        val pos = scanner.nextInt()
        val color = scanner.nextInt()
        arr[color].add(pos)
    }
    arr.forEach {
        if (it.isEmpty())
            return@forEach
        it.sort()
        it.forEachIndexed { index, i ->
            sum += when (index) {
                0 -> it[index + 1] - it[index]
                it.lastIndex -> it[it.lastIndex] - it[it.lastIndex - 1]
                else -> min(it[index + 1] - it[index], it[index] - it[index - 1])
            }
        }
    }
    print(sum)
    scanner.close()
}