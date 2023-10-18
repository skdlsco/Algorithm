package `15726`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (A, B, C) = reader.readLine().split(" ").map { it.toDouble() }

    println(max(A * B / C, A / B * C).toInt())
}