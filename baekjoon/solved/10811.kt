package `10811`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Int>(N) { it + 1 }
    repeat(M) {
        val (i, j) = reader.readLine().split(" ").map { it.toInt() - 1 }
        for (offset in 0..(j - i) / 2) {
            val temp = arr[i + offset]
            arr[i + offset] = arr[j - offset]
            arr[j - offset] = temp
        }
    }
    println(arr.joinToString(" "))
}