package `28065`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    writer.write("$N ")
    var d = 1
    for (i in 0 until N - 1) {
        if (i % 2 == 0)
            writer.write("${d} ")
        else {
            writer.write("${N - d} ")
            d++
        }
    }
    writer.flush()
}