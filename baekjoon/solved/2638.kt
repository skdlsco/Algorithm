package baekjoon.solved

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

data class Point(val x: Int, val y: Int)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Int>>(N) { reader.readLine().split(" ").map { it.toInt() }.toTypedArray() }
    map[0][0] = -1

    var phase = 0
    var findCheese = true
    val queue: Queue<Point> = LinkedList()
    val visited = Array<Array<Int>>(N) { Array(M) { 0 } }
    queue.add(Point(0, 0))
    visited[0][0] = -1
    while (findCheese) {
        findCheese = false
        // bfs
        while (queue.isNotEmpty()) {
            val point = queue.remove()
            for (i in 0 until 4) {
                val nextX = point.x + dx[i]
                val nextY = point.y + dy[i]
                if (nextX >= 0 && nextX < M && nextY >= 0 && nextY < N) {
                    val cell = map[nextY][nextX]
                    if (cell == 1) {
                        visited[nextY][nextX]++
                        findCheese = true
                    } else if (cell == 0 && visited[nextY][nextX] == 0) {
                        visited[nextY][nextX] = -1
                        queue.add(Point(nextX, nextY))
                    }
                }
            }
        }

        visited.forEachIndexed { y, ints ->
            ints.forEachIndexed { x, i ->
                if (i >= 2) {
                    queue.add(Point(x, y))
                    map[y][x] = 0
                    visited[y][x] = -1

                }
            }
        }
        phase++
    }
    println(phase - 1)
}