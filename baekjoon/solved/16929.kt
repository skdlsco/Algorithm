package `16929`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

data class Pos(val x: Int, val y: Int)

const val Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
val dy = arrayOf(0, 0, -1, 1)
val dx = arrayOf(1, -1, 0, 0)
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Char>>(N) { Array(M) { ' ' } }
    val visited = Array<Array<Boolean>>(N) { Array(M) { false } }

    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until M) {
            map[y][x] = line[x]
        }
    }
    var isFind = false
    for (target in Alphabet) {
        while (true) {
            var startX = -1
            var startY = -1
            for (y in 0 until N) {
                if (startY != -1)
                    break
                for (x in 0 until M) {
                    if (target == map[y][x] && !visited[y][x]) {
                        startY = y
                        startX = x
                    }
                }
            }
            if (startX == -1)
                break
            val queue = LinkedList<Pair<Pos, Pos>>()
            queue.add(Pair(Pos(startX, startY), Pos(startX, startY)))
            visited[startY][startX] = true
            while (queue.isNotEmpty() && !isFind) {
                val (prev, cur) = queue.pop()
                for (i in 0 until 4) {
                    val next = Pos(cur.x + dx[i], cur.y + dy[i])
                    if (next.x in 0 until M && next.y in 0 until N && map[next.y][next.x] == target) {
                        if (visited[next.y][next.x] && next != prev) {
                            isFind = true
                            break
                        }
                        if (!visited[next.y][next.x] && next != prev) {
                            queue.add(Pair(cur, next))
                            visited[next.y][next.x] = true
                        }
                    }
                }
            }
        }
    }
    writer.write(if (isFind) "Yes" else "No")
    writer.flush()
}