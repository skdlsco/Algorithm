package `15685`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val MAX_X = 100
val MAX_Y = 100

val dx = arrayOf(1, 0, -1, 0)
val dy = arrayOf(0, -1, 0, 1)

val canvas = Array<Array<Boolean>>(MAX_Y + 1) { Array(MAX_X + 1) { false } }
val checkX = arrayOf(0, 1, 0, 1)
val checkY = arrayOf(0, 0, 1, 1)


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()

    repeat(N) {
        val (x, y, d, g) = reader.readLine().split(" ").map { it.toInt() }
        val route = ArrayList<Int>()
        route.add(d)
        repeat(g) {
            val append = route.reversed().map { (it + 1) % 4 }
            route.addAll(append)
        }
        var curX = x
        var curY = y
        canvas[curY][curX] = true
        route.forEach {
            curX += dx[it]
            curY += dy[it]
            canvas[curY][curX] = true
        }
    }
    var cnt = 0
    for (y in 0 until MAX_Y) {
        for (x in 0 until MAX_X) {
            if ((0..3).all { canvas[y + checkY[it]][x + checkX[it]] })
                cnt++
        }
    }
    writer.write("${cnt}\n")
    writer.flush()
}