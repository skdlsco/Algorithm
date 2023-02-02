package `2448`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun draw(canvas: Array<Array<Char>>, N: Int, x: Int, y: Int) {
    if (N == 3) {
        canvas[y][x + 2] = '*'
        canvas[y + 1][x + 1] = '*'
        canvas[y + 1][x + 3] = '*'
        canvas[y + 2][x] = '*'
        canvas[y + 2][x + 1] = '*'
        canvas[y + 2][x + 2] = '*'
        canvas[y + 2][x + 3] = '*'
        canvas[y + 2][x + 4] = '*'
        return
    }
    draw(canvas, N / 2, x + N / 2, y)
    draw(canvas, N / 2, x, y + N / 2)
    draw(canvas, N / 2, x + N, y + N / 2)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val canvas = Array<Array<Char>>(N) { Array(N * 2) { ' ' } }

    draw(canvas, N, 0, 0)
    canvas.forEach {
        writer.write(it.joinToString(""))
        writer.newLine()
    }
    writer.flush()
}