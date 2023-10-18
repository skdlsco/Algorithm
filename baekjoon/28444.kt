package `28444`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (H, I, A, R, C) = reader.readLine().split(" ").map { it.toInt() }
    writer.write("${(H * I) - (A * R * C)}\n")
    writer.flush()
}