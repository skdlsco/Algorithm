package `2750`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val arr = Array<Int>(N) { reader.readLine().toInt() }

    for (i in N - 1 downTo 0) {
        for (j in 0 until i) {
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
    println(arr.joinToString("\n"))
}