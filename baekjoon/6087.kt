package `6087`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import kotlin.math.min

data class Point(val x: Int, val y: Int, val d: Int)

val dx = arrayOf(0, 0, 1, -1, 0)
val dy = arrayOf(1, -1, 0, 0, 0)
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (W, H) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Char>>(H) { Array(W) { '.' } }
    var start = Point(-1, -1, 4)
    var end = Point(-1, -1, 4)
    for (y in 0 until H) {
        val line = reader.readLine()
        for (x in 0 until W) {
            if (line[x] == 'C') {
                if (start.x == -1)
                    start = Point(x, y, 4)
                else
                    end = Point(x, y, 4)
                map[y][x] = '.'
            } else
                map[y][x] = line[x]
        }
    }
    val visited = Array<Array<Array<Int>>>(H) { Array(W) { Array(5) { Int.MAX_VALUE } } }
    val queue = LinkedList<Pair<Int, Point>>()
    queue.add(Pair(0, start))
    visited[start.y][start.x][4] = 0
    var result = Int.MAX_VALUE
    while (queue.isNotEmpty()) {
        val (cur, pos) = queue.pop()

        if (pos.x == end.x && pos.y == end.y) {
            result = min(result, cur)
            continue
        }
        if (pos.d == 4) {
            for (i in 0 until 4) {
                val nextX = pos.x + dx[i]
                val nextY = pos.y + dy[i]
                if (nextX in 0 until W && nextY in 0 until H
                    && visited[nextY][nextX][i] > cur && map[nextY][nextX] == '.'
                ) {
                    queue.add(Pair(cur, Point(nextX, nextY, i)))
                    visited[nextY][nextX][i] = cur
                }
            }
        } else {
            val nextX = pos.x + dx[pos.d]
            val nextY = pos.y + dy[pos.d]
            if (nextX in 0 until W && nextY in 0 until H
                && visited[nextY][nextX][pos.d] > cur && map[nextY][nextX] == '.'
            ) {
                queue.add(Pair(cur, Point(nextX, nextY, pos.d)))
                visited[nextY][nextX][pos.d] = cur
            }
            if (visited[pos.y][pos.x][4] > cur + 1) {
                queue.add(Pair(cur + 1, Point(pos.x, pos.y, 4)))
                visited[pos.y][pos.x][pos.d] = cur + 1
            }
        }
    }
    writer.write("${result}")
    writer.flush()
}