package `A`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val t =reader.readLine().toInt()
    val ans = (0 until t).map { reader.readLine() }.count { it.contains("01") or it.contains("OI") }
    writer.write("${ans}\n")
    writer.flush()
}
    