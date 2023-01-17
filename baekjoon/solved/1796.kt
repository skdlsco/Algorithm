package `1796`

import kotlin.math.abs


fun find(depth: Int, arr: List<List<Int>>, start: Int): Int {
    if (depth >= 27)
        return 0
    if (arr[depth].isEmpty())
        return find(depth + 1, arr, start)
    return arr[depth].minOf {
        val min = arr[depth].first()
        val max = arr[depth].last()
        val move = if (start < it) {
            abs(start - min) + (max - min) + (max - it)
        } else {
            abs(max - start) + (max - min) + (it - min)
        }
        find(depth + 1, arr, it) + move
    }
}

fun main() {
    val s = readLine()!!
    val arr = List<ArrayList<Int>>(27) { ArrayList() }
    s.forEachIndexed { index, c ->
        val alphabet = c.code - 'a'.code
        arr[alphabet].add(index)
    }
    arr.forEach { it.sort() }
    val result = find(0, arr, 0) + s.length
    println(result)
}

