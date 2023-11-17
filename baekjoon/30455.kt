package `B`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N =reader.readLine().toInt()
    if (N % 2 == 0)
        writer.write("Duck")
    else
        writer.write("Goose")
    writer.flush()
}
    