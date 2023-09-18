package `30017`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (A, B) = reader.readLine().split(" ").map { it.toInt() }
    val cheese = min(A - 1, B)
    val patty = min(B + 1, A)
    writer.write("${patty + cheese}\n")
    writer.flush()
}