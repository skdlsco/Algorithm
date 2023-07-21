package `9328`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

data class Pos(val x: Int, val y: Int)

class Solution(
    val h: Int, val w: Int,
    val map: Array<Array<Char>>, val hasKey: Array<Boolean>
) {
    var stolenCnt = 0
    val visited = Array<Array<Boolean>>(h) { Array(w) { false } }
    val keyDoor = Array<LinkedList<Pos>>(26) { LinkedList() }
    fun solve(): Int {
        val queue = LinkedList<Pos>()
        visited[0][0] = true
        queue.add(Pos(0, 0))
        while (queue.isNotEmpty()) {
            val (x, y) = queue.pop()
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (ny !in 0 until h || nx !in 0 until w || map[ny][nx] == '*' || visited[ny][nx])
                    continue
                if (map[ny][nx].isLowerCase()) {
                    val keyCode = map[ny][nx].code - 'a'.code
                    queue.add(Pos(nx, ny))
                    hasKey[keyCode] = true
                    queue.addAll(keyDoor[keyCode])
                    keyDoor[keyCode].clear()
                }
                if (map[ny][nx].isUpperCase()) {
                    val keyCode = map[ny][nx].code - 'A'.code
                    if (hasKey[keyCode]) {
                        queue.add(Pos(nx, ny))
                    } else {
                        keyDoor[keyCode].add(Pos(nx, ny))
                    }
                }
                if (map[ny][nx] == '$') {
                    queue.add(Pos(nx, ny))
                    stolenCnt++
                }
                if (map[ny][nx] == '.')
                    queue.add(Pos(nx, ny))
                visited[ny][nx] = true
            }
        }
        return stolenCnt
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()

    repeat(T) {
        val (h, w) = reader.readLine().split(" ").map { it.toInt() }
        val map = Array<Array<Char>>(h + 2) { Array(w + 2) { '.' } }
        for (y in 1..h) {
            val row = reader.readLine()
            for (x in 1..w) {
                map[y][x] = row[x - 1]
            }
        }
        val keys = reader.readLine()
        val hasKey = Array<Boolean>(26) { false }
        if (keys != "0") {
            for (key in keys) {
                val idx = key.code - 'a'.code
                hasKey[idx] = true
            }
        }

        val result = Solution(h + 2, w + 2, map, hasKey).solve()
        writer.write("${result}\n")
    }
    writer.flush()
}