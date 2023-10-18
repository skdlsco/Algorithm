package `29558`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, D) = reader.readLine().split(" ").map { it.toInt() }
    if (N % 2 == 1)
        writer.write("${D} ")
    for (i in 1..N / 2) {
        writer.write("${D + i} ")
        writer.write("${D - i} ")
    }
    writer.flush()
}
    