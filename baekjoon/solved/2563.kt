package `2563`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val board = Array<Array<Boolean>>(100) { Array(100) { false } }
    val N = reader.readLine().toInt()
    repeat(N) {
        val (sx, sy) = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until 10) {
            for (y in 0 until 10) {
                board[sy + y][sx + x] = true
            }
        }
    }
    writer.write("${board.sumOf { it.count { it }}}\n")
    writer.flush()
}