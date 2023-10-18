package A

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val input = reader.readLine()
    val ans = when (input) {
        "M" -> "MatKor"
        "W" -> "WiCys"
        "C" -> "CyKor"
        "A" -> "AlKor"
        "\$" -> "\$clear"
        else -> ""
    }
    writer.write(ans)
    writer.flush()
}