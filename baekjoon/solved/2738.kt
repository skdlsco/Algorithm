package `2738`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val matrix = Array<Array<Int>>(N) { Array(M) { 0 } }
    repeat(2) {
        for (y in 0 until N) {
            val line = reader.readLine().split(" ").map { it.toInt() }
            for (x in 0 until M) {
                matrix[y][x] += line[x]
            }
        }
    }
    writer.write(matrix.joinToString("\n") { it.joinToString(" ") })
    writer.flush()
}