package `7562`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList


val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

val dx = arrayOf(1, 2, 2, 1, -1, -2, -2, -1)
val dy = arrayOf(2, 1, -1, -2, -2, -1, 1, 2)

data class Data(val x: Int, val y: Int, val turn: Int)

fun solve(): Int {
    val I = reader.readLine().toInt()
    val visited = Array<Array<Boolean>>(I) { Array(I) { false } }
    val (startX, startY) = reader.readLine().split(" ").map { it.toInt() }
    val (endX, endY) = reader.readLine().split(" ").map { it.toInt() }
    val queue = LinkedList<Data>()

    queue.add(Data(startX, startY, 0))
    while (queue.isNotEmpty()) {
        val (x, y, turn) = queue.pop()
        if (x == endX && y == endY)
            return turn
        val nextTurn = turn + 1
        for (i in 0 until 8) {
            val nextX = x + dx[i]
            val nextY = y + dy[i]
            if (nextX in 0 until I && nextY in 0 until I &&
                !visited[nextY][nextX]
            ) {
                queue.add(Data(nextX, nextY, nextTurn))
                visited[nextY][nextX] = true
            }
        }
    }
    return 0
}

fun main() {
    val T = reader.readLine().toInt()
    repeat(T) {
        writer.write("${solve()}\n")
    }
    writer.flush()
}