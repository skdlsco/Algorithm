package `10810`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Int>(N + 1) { 0 }
    repeat(M) {
        var (i, j, k) = reader.readLine().split(" ").map { it.toInt() }
        while (i <= j) {
            arr[i] = k
            i++
        }
    }
    println(arr.slice(1..N).joinToString(" "))
}