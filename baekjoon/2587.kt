package `2587`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val arr = Array<Int>(5) { reader.readLine().toInt() }
    arr.sort()
    println(arr.sum() / 5)
    println(arr[2])
}