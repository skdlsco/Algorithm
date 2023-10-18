package `1261`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

data class Data(val x: Int, val y: Int, val count: Int)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (M, N) = reader.readLine().split(" ").map { it.toInt() }
    // true: wall
    val map = Array<Array<Boolean>>(N) { Array(M) { false } }
    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until M) {
            map[y][x] = line[x] == '1'
        }
    }
    // 부순 벽의 수
    val visited = Array<Array<Int>>(N) { Array(M) { -1 } }
    val queue = LinkedList<Data>()
    queue.add(Data(0, 0, 0))
    visited[0][0] = 0
    while (queue.isNotEmpty()) {
        val (x, y, count) = queue.pop()
        for (i in 0 until 4) {
            val nextX = x + dx[i]
            val nextY = y + dy[i]
            if (nextX in 0 until M && nextY in 0 until N) {
                val nextCount = count + if (map[nextY][nextX]) 1 else 0
                if (visited[nextY][nextX] == -1 || visited[nextY][nextX] > nextCount) {
                    visited[nextY][nextX] = nextCount
                    queue.add(Data(nextX, nextY, nextCount))
                }
            }
        }
    }
    writer.write("${visited[N - 1][M - 1]}")
    writer.flush()
}