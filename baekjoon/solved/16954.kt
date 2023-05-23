package `16954`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

val dx = arrayOf(-1, -1, -1, 0, 0, 0, 1, 1, 1)
val dy = arrayOf(1, 0, -1, 1, 0, -1, 1, 0, -1)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val board = Array<Array<Char>>(8) { Array(8) { '.' } }
    for (y in 0 until 8) {
        val line = reader.readLine()
        for (x in 0 until 8) {
            board[y][x] = line[x]
        }
    }
    var nextLevel = 1
    val queue = LinkedList<Pair<Pair<Int, Int>, Int>>()
    val visited = Array<Array<Int>>(8) { Array(8) { 0 } }
    queue.add(Pair(Pair(0, 7), 0))
    var moveable = false
    while (queue.isNotEmpty() && !moveable) {
        val (pos, level) = queue.pop()
        val (x, y) = pos
        if (x == 7 && y == 0) {
            moveable = true
            break
        }
        if (level == nextLevel) {
            for (by in 6 downTo 0) {
                for (bx in 0 until 8) {
                    board[by + 1][bx] = board[by][bx]
                    board[by][bx] = '.'
                }
            }
            nextLevel++
        }
        if (board[y][x] == '#')
            continue
        for (i in dx.indices) {
            val nextX = x + dx[i]
            val nextY = y + dy[i]
            if (nextX in 0 until 8 && nextY in 0 until 8 && board[nextY][nextX] != '#' && visited[nextY][nextX] != nextLevel) {
                queue.add(Pair(Pair(nextX, nextY), nextLevel))
                visited[nextY][nextX] = nextLevel
            }
        }
    }
    writer.write("${if (moveable) 1 else 0}")
    writer.flush()
}