package `9659`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toLong()
    if (N % 2 == 1L)
        writer.write("SK\n")
    else
        writer.write("CY\n")
    writer.flush()
}