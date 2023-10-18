package `27922`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Array<Int>>(N) { reader.readLine().split(" ").map { it.toInt() }.toTypedArray() }

    val ab = arr.sortedByDescending {
        it[0] + it[1]
    }.subList(0, K).sumOf { it[0] + it[1] }
    val bc = arr.sortedByDescending {
        it[1] + it[2]
    }.subList(0, K).sumOf { it[1] + it[2] }
    val ac = arr.sortedByDescending {
        it[0] + it[2]
    }.subList(0, K).sumOf { it[0] + it[2] }
    writer.write("${max(ab, max(bc, ac))}")
    writer.flush()
}