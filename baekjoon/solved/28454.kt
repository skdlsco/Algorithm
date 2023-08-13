package `28454`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val now = reader.readLine()
    val N = reader.readLine().toInt()
    val ans = (0 until N).count {
        now <= reader.readLine()
    }
    writer.write("${ans}\n")
    writer.flush()
}