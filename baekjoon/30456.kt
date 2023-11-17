package `C`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, L) = reader.readLine().split(" ").map { it.toInt() }

    if (N == 0) {
        writer.write("1")
        writer.write((0 until L - 1).joinToString("") { "0" })
    } else {
        writer.write("${N}")
        writer.write((0 until L - 1).joinToString("") { "1" })
    }
    writer.flush()
}
    