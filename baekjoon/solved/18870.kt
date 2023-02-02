package `18870`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun getResult(arr: Array<Int>, n: Int): Int {
    var left: Int = 0
    var right: Int = arr.lastIndex
    while (left <= right) {
        val mid = (left + right) / 2
        if (arr[mid] < n)
            left = mid + 1
        else if (arr[mid] > n)
            right = mid - 1
        else
            return (mid)
    }
    return (right)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val stringTokenizer = StringTokenizer(reader.readLine())
    val arr = Array<Int>(N) { stringTokenizer.nextToken().toInt() }
    val copy = arr.toSortedSet().toTypedArray()
    println(arr.joinToString( separator = " ") {
        "${getResult(copy, it)}"
    })
}