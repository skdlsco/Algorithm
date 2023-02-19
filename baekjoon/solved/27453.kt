package `27453`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

class Point(val y: Int, val x: Int, val k: Int, val length: Int)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }

    // y, x, k if (k == -1, 벽)
    val map = Array<Array<Int>>(N) { Array(M) { 0 } }
    // 각 visited 칸에 어디에서 왔는지 저장
    // y, x, k
    val visited = Array<Array<Array<Int>>>(N) { Array(M) { Array(5) { Int.MAX_VALUE } } }
    var start = Point(0, 0, 0, 0)
    var destination = Point(0, 0, 0, 0)
    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until M) {
            when (line[x]) {
                'S' -> start = Point(y, x, 0, 0)
                'H' -> destination = Point(y, x, 0, 0)
                'X' -> map[y][x] = -1
                else -> map[y][x] = line[x].digitToInt()
            }
        }
    }
    val queue = LinkedList<Pair<Point, Point>>()
    visited[start.y][start.x][4] = start.length
    queue.add(Pair(start, start))
    while (queue.isNotEmpty()) {
        val (prev, now) = queue.pop()
        repeat(4) {
            val nextX = dx[it] + now.x
            val nextY = dy[it] + now.y
            val nextLength = now.length + 1
            if ((nextX != prev.x || nextY != prev.y) &&
                (nextY in 0 until N && nextX in 0 until M) &&
                map[nextY][nextX] != -1
            ) {
                val nextK = map[nextY][nextX]
                val sum = nextK + now.k + prev.k
                if (sum <= K && visited[nextY][nextX][it] > nextLength) {
                    visited[nextY][nextX][it] = nextLength
                    queue.add(Pair(now, Point(nextY, nextX, nextK, nextLength)))
                }
            }
        }
    }

    val result = visited[destination.y][destination.x].minOf { it }
    if (result == Int.MAX_VALUE)
        println(-1)
    else
        println(result)
}