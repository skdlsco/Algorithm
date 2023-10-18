package `28682`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    writer.write((0 until n).map { "swimming" }.joinToString(" "))
    writer.newLine()
    writer.flush()
    val arr = reader.readLine().trim().split(" ")
    val result = StringBuilder()
    for (str in arr) {
        if (str == "bowling")
            result.append("soccer ")
        else
            result.append("bowling ")
    }
    writer.write(result.toString())
    writer.newLine()
    writer.flush()
}