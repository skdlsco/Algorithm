package `29703`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

data class Data(val x: Int, val y: Int, val dist: Int = 0, val isSuccess: Boolean = false)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Char>>(N) { Array(M) { ' ' } }

    var start = Data(0, 0)
    var home = Data(0, 0)
    for (y in 0 until N) {
        val row = reader.readLine()
        for (x in 0 until M) {
            map[y][x] = row[x]
            if (map[y][x] == 'S')
                start = Data(x, y)
            if (map[y][x] == 'H')
                home = Data(x, y)
        }
    }

    val visited = Array<Array<Array<Boolean>>>(N) { Array(M) { Array(2) { false } } }
    visited[start.y][start.x][0] = true
    writer.flush()
    val queue = LinkedList<Data>()
    queue.add(start)
    var ans = -1
    while (queue.isNotEmpty()) {
        val data = queue.pop()
        if (data.x == home.x && data.y == home.y && data.isSuccess) {
            ans = data.dist
            break
        }
        for (i in 0 until 4) {
            val nx = data.x + dx[i]
            val ny = data.y + dy[i]
            if (ny in 0 until N && nx in 0 until M && map[ny][nx] != 'D') {
                val isSuccess = data.isSuccess || map[ny][nx] == 'F'
                val t = if (isSuccess) 1 else 0
                if (!visited[ny][nx][t]) {
                    visited[ny][nx][t] = true
                    queue.add(Data(nx, ny, data.dist + 1, isSuccess))
                }
            }
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}
    