package `14940`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    var start = Pair<Int, Int>(-1, -1)
    val map = Array<Array<Int>>(n) { Array(m) { -1 } }
    for (y in 0 until n) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until m) {
            if (row[x] == 2)
                start = Pair(x, y)
            else if (row[x] == 0)
                map[y][x] = 0
        }
    }
    val queue = LinkedList<Pair<Int, Pair<Int, Int>>>()
    queue.add(Pair(0, start))

    map[start.second][start.first] = 0
    while (queue.isNotEmpty()) {
        val (depth, point) = queue.pop()
        val (curX, curY) = point

        for (i in 0 until 4) {
            val nx = curX + dx[i]
            val ny = curY + dy[i]
            if (nx in 0 until m && ny in 0 until n && map[ny][nx] == -1) {
                map[ny][nx] = depth + 1
                queue.add(Pair(depth + 1, Pair(nx, ny)))
            }
        }
    }
    writer.write(map.joinToString("\n") { it.joinToString(" ") })
    writer.flush()
}