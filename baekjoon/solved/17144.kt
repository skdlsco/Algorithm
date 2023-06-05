package `17144`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (R, C, T) = reader.readLine().split(" ").map { it.toInt() }
    var room = Array<Array<Int>>(R) { Array(C) { 0 } }

    var purifierPos = -1
    for (y in 0 until R) {
        val line = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until C) {
            room[y][x] = line[x]
            if (line[x] == -1 && purifierPos == -1)
                purifierPos = y
        }
    }

    repeat(T) {
        val nextRoom = Array<Array<Int>>(R) { Array(C) { 0 } }
        // 확산
        for (y in 0 until R) {
            for (x in 0 until C) {
                if (room[y][x] == -1) {
                    nextRoom[y][x] = -1
                    continue
                }
                var cnt = 0
                for (i in 0 .. 3) {
                    val ny = y + dy[i]
                    val nx = x + dx[i]
                    if (ny in 0 until R && nx in 0 until C && room[ny][nx] != -1) {
                        cnt++
                        nextRoom[ny][nx] += room[y][x] / 5
                    }
                }
                nextRoom[y][x] += room[y][x] - ((room[y][x] / 5) * cnt)
            }
        }
        // 청정기 작동
        var prev = 0
        for (x in 1 until C) {
            val temp = nextRoom[purifierPos][x]
            nextRoom[purifierPos][x] = prev
            prev = temp
        }
        for (y in purifierPos - 1 downTo 0) {
            val temp = nextRoom[y][C - 1]
            nextRoom[y][C - 1] = prev
            prev = temp
        }
        for (x in C - 2 downTo 0) {
            val temp = nextRoom[0][x]
            nextRoom[0][x] = prev
            prev = temp
        }
        for (y in 1 until purifierPos) {
            val temp = nextRoom[y][0]
            nextRoom[y][0] = prev
            prev = temp
        }
        prev = 0
        for (x in 1 until C) {
            val temp = nextRoom[purifierPos + 1][x]
            nextRoom[purifierPos + 1][x] = prev
            prev = temp
        }
        for (y in purifierPos + 2 until R) {
            val temp = nextRoom[y][C - 1]
            nextRoom[y][C - 1] = prev
            prev = temp
        }
        for (x in C - 2 downTo 0) {
            val temp = nextRoom[R - 1][x]
            nextRoom[R - 1][x] = prev
            prev = temp
        }
        for (y in R - 2 downTo purifierPos + 2) {
            val temp = nextRoom[y][0]
            nextRoom[y][0] = prev
            prev = temp
        }
        room = nextRoom
    }
    writer.write("${room.sumOf { it.sumOf { it } } + 2}")
    writer.flush()
}