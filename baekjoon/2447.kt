package `2447`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun draw(canvas: Array<Array<Char>>, N: Int, y: Int, x: Int) {
    if (N == 1) {
        canvas[y][x] = '*'
        return
    }
    val step = N / 3
    for (y1 in y until y + N step step) {
        for (x1 in x until x + N step step) {
            if (y1 == y + step && x1 == x + step)
                continue
            draw(canvas, step, y1, x1)
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val canvas = Array<Array<Char>>(N) { Array(N) { ' ' } }

    draw(canvas, N, 0, 0)
    canvas.forEach {
        writer.write(it.joinToString(""))
        writer.write("\n")
    }
    writer.flush()
    writer.close()
}