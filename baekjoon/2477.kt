package `2477`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.ceil
import kotlin.math.log2

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val time = reader.readLine().toInt()
    val arr = Array<Int>(6) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        b
    }
    var big = 0
    var bigStartIdx = -1
    repeat(6) {
        val now = arr[it] * arr[(it + 1) % 6]
        if (big < now) {
            big = now
            bigStartIdx = it
        }
    }
    println((big - arr[(bigStartIdx + 3) % 6] * arr[(bigStartIdx + 4) % 6]) * time);
}
