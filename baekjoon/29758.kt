package `29758`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.min
import kotlin.math.sqrt

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val (N, L) = reader.readLine().split(" ").map { it.toInt() }
        val ans = solve(N, L)
        writer.write("${ans}\n")
    }
    writer.flush()
}

fun solve(N: Int, L: Int): Long {
    if (L > 5)
        return 0
    if (L == 1)
        return if (N == 1) 1 else 0
    if (L == 2)
        return if (N > 1) 1 else 0
    var sum = 0L
    val max = ceil(sqrt(N.toDouble())).toInt() - 1
    for (i in 1..max) {
        sum += solve(i, L - 1)
    }
    return sum
}
    