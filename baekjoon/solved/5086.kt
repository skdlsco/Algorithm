package `5086`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        if (a == 0 && b == 0)
            break
        if (a >= b && a % b == 0)
            writer.write("multiple")
        else if (a <= b && b % a == 0)
            writer.write("factor")
        else
            writer.write("neither")
        writer.newLine()
    }
    writer.flush()
}