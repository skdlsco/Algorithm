package `19645`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val burger = reader.readLine().split(" ").map { it.toInt() }.sortedDescending()
    val arr = Array<Int>(3) { 0 }
    var left = burger.sum()
    burger.forEach {
        left -= it
        if (arr[2] - arr[0] > left + it && arr[1] - arr[0] <= left)
            arr[1] += it
        else
            arr[0] += it
        arr.sort()
    }
    println(arr[0])
}