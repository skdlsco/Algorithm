package `30036`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

data class Pos(val x: Int, val y: Int)

val dx = arrayOf(0, 0, -1, 1)
val dy = arrayOf(-1, 1, 0, 0)
val direction = "UDLR"

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (I, N, K) = reader.readLine().split(" ").map { it.toInt() }
    val ink = reader.readLine()
    var pos = Pos(0, 0)
    var power = 0
    val board = Array<Array<Char>>(N) { Array(N) { '.' } }
    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until N) {
            board[y][x] = line[x]
            if (line[x] == '@') {
                pos = Pos(x, y)
                board[y][x] = '.'
            }
        }
    }
    val commands = reader.readLine()
    var jumpCnt = 0
    for (command in commands) {
        when (command) {
            'j' -> power++
            'J' -> {
                val color = ink[jumpCnt % I]
                for (y in 0 until N) {
                    for (x in 0 until N) {
                        if (abs(pos.x - x) + abs(pos.y - y) <= power && board[y][x] != '.')
                            board[y][x] = color
                    }
                }
                jumpCnt++
                power = 0
            }

            else -> {
                val d = direction.indexOf(command)
                val ny = dy[d] + pos.y
                val nx = dx[d] + pos.x
                if (nx in 0 until N && ny in 0 until N && board[ny][nx] == '.') {
                    pos = Pos(nx, ny)
                }
            }
        }
    }
    for (y in 0 until N) {
        for (x in 0 until N) {
            if (pos.y == y && pos.x == x)
                writer.write("@")
            else
                writer.write("${board[y][x]}")
        }
        writer.newLine()
    }
    writer.flush()
}
