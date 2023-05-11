package `2444`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    for (i in 1 until N * 2) {
        for (j in 0 until abs(N - i)) {
            writer.write(" ")
        }
        for (j in 1 until (N * 2) - 2 * abs(N - i)) {
            writer.write("*")
        }
        writer.newLine()
    }
    writer.flush()
}