package `27961`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toLong()
    if (N == 0L)
        println(0)
    else {
        var i = 1
        var n = 1L
        while (n < N) {
            i++
            n *= 2L
        }
        println(i)
    }
}