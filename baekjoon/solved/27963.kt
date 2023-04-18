package `27963`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (d1, d2, X) = reader.readLine().split(" ").map { it.toDouble() }

    println(1 / (1 / (max(d1, d2)) * (X / 100) + 1 / min(d1, d2) * (100 - X) / 100))
}