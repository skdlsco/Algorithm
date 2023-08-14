package `28701`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val A = (1..N).sumOf { it }
    writer.write("${A}\n")
    writer.write("${A * A}\n")
    writer.write("${(1..N).sumOf { it * it * it }}\n")
    writer.flush()
}