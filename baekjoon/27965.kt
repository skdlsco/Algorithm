package `27965`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, K) = reader.readLine().split(" ").map { it.toLong() }
    var result = 0L
    for (i in 1..N) {
        var cur = i
        while (cur > 0) {
            cur /= 10L
            result *= 10L
        }
        result += i
        result %= K
    }
    println(result)
}