package `1600`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

data class Data(val x: Int, val y: Int, val k: Int)

val dx = arrayOf(1, 2, 2, 1, -1, -2, -2, -1, 1, -1, 0, 0)
val dy = arrayOf(2, 1, -1, -2, -2, -1, 1, 2, 0, 0, 1, -1)
val dk = arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val K = reader.readLine().toInt()
    val (W, H) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Boolean>>(H) { Array(W) { false } }
    val visited = Array<Array<Array<Boolean>>>(H) { Array(W) { Array(K + 1) { false } } }
    for (y in 0 until H) {
        val line = reader.readLine().split(" ").map { it == "0" }
        for (x in 0 until W) {
            map[y][x] = line[x]
        }
    }
    val queue = LinkedList<Pair<Int, Data>>()
    queue.add(Pair(0, Data(0, 0, 0)))
    visited[0][0][0] = true

    var result = -1
    while (queue.isNotEmpty()) {
        val (time, cur) = queue.pop()

        if (cur.x == W - 1 && cur.y == H - 1) {
            result = time
            break
        }
        for (i in 0 until dx.size) {
            val nextX = cur.x + dx[i]
            val nextY = cur.y + dy[i]
            val nextK = cur.k + dk[i]
            if (nextK <= K && nextX in 0 until W && nextY in 0 until H &&
                map[nextY][nextX] && !visited[nextY][nextX][nextK]
            ) {
                queue.add(Pair(time + 1, Data(nextX, nextY, nextK)))
                visited[nextY][nextX][nextK] = true
            }
        }
    }
    writer.write("$result")
    writer.flush()
}