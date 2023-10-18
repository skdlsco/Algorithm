package `10972`

import java.io.BufferedReader
import java.io.InputStreamReader

fun getStartPoint(visited: BooleanArray, arr: Array<Int>): Int {
    arr.forEachIndexed { index, it ->
        visited[it] = false
        if (visited.lastIndexOf(false) != it)
            return index
        arr[index] = 0
    }
    return -1
}

fun getNextNum(visited: BooleanArray, now: Int): Int {
    visited.forEachIndexed { index, b ->
        if (!b && index > now)
            return index
    }
    return now
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }.reversed().toTypedArray()
    val visited = BooleanArray(N + 1) { true }
    val tmp = getStartPoint(visited, arr)
    if (tmp != -1) {
        (tmp downTo 0).forEach {
            val num = getNextNum(visited, arr[it])
            arr[it] = num
            visited[num] = true
        }
        println(arr.reversed().joinToString(" "))
    } else
        println(-1)
}
