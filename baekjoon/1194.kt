package `1194`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

// key 소유는 bit
data class Pos(val key: Int, val x: Int, val y: Int)

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    // key,y,x
    val map = Array<Array<Char>>(N) { Array(M) { '.' } }
    var start = Pos(0, 0, 0)
    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until M) {
            map[y][x] = line[x]
            if (map[y][x] == '0') {
                start = Pos(0, x, y)
            }
        }
    }
    //a ~ f empty, y,x
    val visited = Array<Array<Array<Boolean>>>(1 shl 7) { Array(N) { Array(M) { false } } }
    val queue: Queue<Pair<Int, Pos>> = LinkedList()
    queue.add(Pair(0, start))
    visited[0][start.y][start.x] = true
    var result = -1
    while (queue.isNotEmpty()) {
        val now = queue.remove()

        repeat(4) {
            val nextX = now.second.x + dx[it]
            val nextY = now.second.y + dy[it]
            if (result != -1 || nextY !in 0 until N || nextX !in 0 until M)
                return@repeat
            val cell = map[nextY][nextX]
            // 해당 열쇠가 있는지 && 방문체크
            if (cell != '#'
                && !visited[now.second.key][nextY][nextX]
            ) {
                when (cell) {
                    // 문칸인 경우
                    in "ABCDEF" -> {
                        if (1 shl (cell.code - 'A'.code) and now.second.key > 0) {
                            queue.add(Pair(now.first + 1, Pos(now.second.key, nextX, nextY)))
                            visited[now.second.key][nextY][nextX] = true
                        }
                    }
                    // 열쇠칸
                    in "abcdef" -> {
                        val nextKey = 1 shl (cell.code - 'a'.code) or now.second.key
                        queue.add(Pair(now.first + 1, Pos(nextKey, nextX, nextY)))
                        visited[nextKey][nextY][nextX] = true
                    }

                    '1' -> {
                        result = now.first + 1
                        queue.clear()
                    }

                    else -> {
                        queue.add(Pair(now.first + 1, Pos(now.second.key, nextX, nextY)))
                        visited[now.second.key][nextY][nextX] = true
                    }
                }
            }
        }
    }
    println(result)
}
