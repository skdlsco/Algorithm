package `2566`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var result = -1
    var maxY = 0
    var maxX = 0
    for (y in 1..9) {
        val line = reader.readLine().split(" ").map { it.toInt() }
        for (x in 1..9) {
            if (line[x - 1] > result) {
                result = line[x - 1]
                maxY = y
                maxX = x
            }
        }
    }
    writer.write("${result}\n")
    writer.write("${maxY} ${maxX}")
    writer.flush()
}