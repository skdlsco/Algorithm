package `16933`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

data class Data(val x: Int, val y: Int, val k: Int, val cnt: Int)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    // false -> wall
    val map = Array<Array<Boolean>>(N) { Array(M) { false } }
    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until M) {
            map[y][x] = line[x] == '0'
        }
    }
    // y, x, time = k
    val visited = Array<Array<Array<Int>>>(N) { Array(M) { Array(2) { 11 } } }
    val queue = LinkedList<Data>()
    queue.add(Data(0, 0, 0, 1))
    visited[0][0][0] = 0

    var result = -1
    while (queue.isNotEmpty()) {
        val (x, y, k, cnt) = queue.pop()
        if (y == N - 1 && x == M - 1) {
            result = cnt
            break
        }
        for (i in dx.indices) {
            val nextX = x + dx[i]
            val nextY = y + dy[i]
            val time = cnt % 2
            if (nextY !in 0 until N || nextX !in 0 until M)
                continue
            // 1 -> 낮
            if (map[nextY][nextX]) {
                if (visited[nextY][nextX][time] > k) {
                    queue.add(Data(nextX, nextY, k, cnt + 1))
                    visited[nextY][nextX][time] = k
                }
            } else if (time == 1 && k < K && visited[nextY][nextX][time] > k + 1) {
                queue.add(Data(nextX, nextY, k + 1, cnt + 1))
                visited[nextY][nextX][time] = k + 1
            } else if (time == 0 && visited[y][x][time] > k) {
                queue.add(Data(x, y, k, cnt + 1))
                visited[y][x][time] = k
            }
        }
    }
    writer.write("${result}")
    writer.flush()
}