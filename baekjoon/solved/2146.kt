package `2146`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import kotlin.math.min

val dx = listOf<Int>(0, 0, 1, -1)
val dy = listOf<Int>(1, -1, 0, 0)

data class Data(val id: Int, val depth: Int, val x: Int, val y: Int)

fun attachIds(N: Int, map: Array<Array<Data>>) {
    var idCnt = 1
    val queue = LinkedList<Data>()

    // 각 섬마다 id 부여
    for (y in 0 until N) {
        for (x in 0 until N) {
            if (map[y][x].id != 0) continue
            map[y][x] = Data(idCnt, 0, x, y)
            queue.clear()
            queue.add(Data(idCnt, 0, x, y))
            while (queue.isNotEmpty()) {
                val (id, _, cx, cy) = queue.pop()
                for (i in 0 until 4) {
                    val nx = cx + dx[i]
                    val ny = cy + dy[i]
                    if (nx in 0 until N && ny in 0 until N && map[ny][nx].id == 0) {
                        val next = Data(id, 0, nx, ny)
                        map[ny][nx] = next
                        queue.add(next)
                    }
                }
            }
            idCnt++
        }
    }
}

fun getResult(N: Int, map: Array<Array<Data>>): Int {
    val queue = LinkedList<Data>()
    // 섬 외곽을 queue에 추가
    for (y in 0 until N) {
        for (x in 0 until N) {
            if (map[y][x].id == -1)
                continue
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx in 0 until N && ny in 0 until N && map[ny][nx].id == -1) {
                    queue.add(map[y][x])
                    break
                }
            }
        }
    }
    // 각 섬 외곽에서부터 탐색 시작
    // 다른 섬영역과 만나면 반환
    var result = Int.MAX_VALUE
    while (queue.isNotEmpty()) {
        val (id, depth, cx, cy) = queue.pop()
        for (i in 0 until 4) {
            val nx = cx + dx[i]
            val ny = cy + dy[i]
            if (nx in 0 until N && ny in 0 until N) {
                if (map[ny][nx].id == -1) {
                    val next = Data(id, depth + 1, nx, ny)
                    map[ny][nx] = next
                    queue.add(next)
                } else if (map[ny][nx].id != id) {
                    result = min(result, map[ny][nx].depth + depth)
                }
            }
        }
    }
    return result
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val map = Array<Array<Data>>(N) { Array(N) { Data(0, 0, 0, 0) } }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N) {
            val id = if (row[x] == 1) 0 else -1
            map[y][x] = Data(id, 0, y, x)
        }
    }
    attachIds(N, map)
    val result = getResult(N, map)
    writer.write("${result}\n")
    writer.flush()
}