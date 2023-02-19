package `1427`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val arr = reader.readLine().map { it.digitToInt() }
    println(arr.sortedDescending().joinToString(""))
}