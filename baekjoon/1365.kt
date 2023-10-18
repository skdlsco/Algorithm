package `1365`

import java.io.BufferedReader
import java.io.InputStreamReader

fun findIndexByUpperBound(subArr: List<Int>, num: Int): Int {
    var left = 0
    var right = subArr.size
    while (left < right) {
        val mid = (left + right) / 2
        if (subArr[mid] >= num)
            right = mid
        else
            left = mid + 1
    }
    return left
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val subArr = ArrayList<Int>()

    for (cur in arr) {
        if (subArr.isEmpty() || subArr.last() < cur) {
            subArr.add(cur)
            continue
        }
        val idx = findIndexByUpperBound(subArr, cur)
        subArr[idx] = cur
    }
    println(N - subArr.size)
}