package `3041`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var sum = 0
    for (y in 0 until 4) {
        reader.readLine().forEachIndexed { x, c ->
            if (c != '.') {
                val idx = c.code - 'A'.code
                val y1 = idx / 4
                val x2 = idx % 4
                sum += abs(y - y1) + abs(x - x2)
            }

        }
    }
    println(sum)
}