package `12348`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val str = reader.readLine()
    val N = str.toLong()

    for (i in str.length * 9 downTo 0) {
        var M = N - i
        var sum = M
        while (M > 0) {
            sum += M % 10
            M /= 10L
        }
        if (sum == N) {
            println(N - i)
            return
        }
    }
    println(0)
}