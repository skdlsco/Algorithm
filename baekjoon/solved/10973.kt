package `10973`

import java.io.BufferedReader
import java.io.InputStreamReader

fun getStartPoint(visited: BooleanArray, arr: Array<Int>): Int {
    arr.forEachIndexed { index, num ->
        visited[num] = false
        if (visited.indexOfFirst { !it } != num)
            return index
        arr[index] = Int.MAX_VALUE
    }
    return -1
}

fun getNextNum(visited: BooleanArray, now: Int): Int {
    (visited.size - 1 downTo 0).forEach {idx ->
        if (!visited[idx] && idx < now)
            return idx
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
