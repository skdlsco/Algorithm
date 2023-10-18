package `14215`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (a, b, c) = reader.readLine().split(" ").map { it.toInt() }.sorted()
    writer.write("${a + b + min(c, a + b - 1)}\n")
    writer.flush()
}