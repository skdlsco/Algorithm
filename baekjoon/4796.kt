package `4796`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    var case = 1
    while (true) {
        val (L, P, V) = reader.readLine().split(" ").map { it.toInt() }
        if (L == 0 && P == 0 && V == 0)
            break
        val sum = (V / P) * L + min(V % P, L)
        println("Case $case: $sum")
        case++
    }
}