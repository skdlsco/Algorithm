package `27918`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    var d = 0
    var p = 0
    for (i in 0 until N) {
        val winner = reader.readLine()
        if (winner == "D") d++
        else p++
        if (abs(d - p) > 1)
            break
    }
    writer.write("${d}:${p}\n")
    writer.flush()
}