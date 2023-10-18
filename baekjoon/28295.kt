package A

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val direction = arrayOf('N', 'E', 'S', 'W')
    var d = 0
    repeat(10) {
        val t = reader.readLine().toInt()
        d += t
    }
    writer.write("${direction[d % 4]}")
    writer.flush()
}