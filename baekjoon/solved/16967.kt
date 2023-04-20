package `16967`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (H, W, X, Y) = reader.readLine().split(" ").map { it.toInt() }
    val A = Array<Array<Int>>(H) { Array(W) { 0 } }
    val B = Array<Array<Int>>(H + X) { Array(W + Y) { 0 } }

    for (y in 0 until H + X) {
        val line = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until W + Y) {
            B[y][x] = line[x]
            if (y < H && x < W)
                A[y][x] = line[x]
        }
    }
    for (y in X until H) {
        for (x in Y until W) {
            A[y][x] -= A[y - X][ x - Y]
        }
    }
    writer.write(A.joinToString("\n") { it.joinToString(" ") })
    writer.flush()
}

