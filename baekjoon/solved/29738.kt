package `29738`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    for (case in 1..T) {
        val N = reader.readLine().toInt()
        val round = if (N > 4500) "Round 1"
        else if (N > 1000) "Round 2"
        else if (N > 25) "Round 3"
        else "World Finals"
        writer.write("Case #${case}: ${round} \n")
    }
    writer.flush()
}
    