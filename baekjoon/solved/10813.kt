package `10813`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Int>(N) { it + 1 }
    repeat(M) {
        val (i, j) = reader.readLine().split(" ").map { it.toInt() - 1 }
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }
    println(arr.joinToString(" "))
}