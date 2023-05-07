package `11718`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    var line: String? = reader.readLine()
    while (line != null) {
        writer.write(line)
        writer.newLine()
        line = reader.readLine()
    }
    writer.flush()
}