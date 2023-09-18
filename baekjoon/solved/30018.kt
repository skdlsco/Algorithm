package `30018`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val A = reader.readLine().split(" ").map { it.toInt() }
    val B = reader.readLine().split(" ").map { it.toInt() }
    val ans = (0 until N).sumOf { abs(A[it] - B[it]) } / 2
    writer.write("${ans}\n")
    writer.flush()
}