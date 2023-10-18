package `16946`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

data class Pos(val x: Int, val y: Int)

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Int>>(N) { Array(M) { 0 } }
    val group = ArrayList<Int>()

    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until M) {
            val cell = line[x].digitToInt()
            map[y][x] = if (cell == 0) 0 else -1
        }
    }
    var groupNum = 1
    group.add(0)
    val queue: Queue<Pos> = LinkedList()
    for (y in 0 until N) {
        for (x in 0 until M) {
            if (map[y][x] != 0)
                continue
            var cnt = 1
            queue.add(Pos(x, y))
            map[y][x] = groupNum
            while (queue.isNotEmpty()) {
                val now = queue.remove()
                repeat(4) {
                    val nextX = now.x + dx[it]
                    val nextY = now.y + dy[it]
                    if (nextX in 0 until M && nextY in 0 until N && map[nextY][nextX] == 0) {
                        queue.add(Pos(nextX, nextY))
                        map[nextY][nextX] = groupNum
                        cnt++
                    }
                }
            }
            group.add(cnt)
            groupNum++
        }
    }

    val set = HashSet<Int>()

    for (y in 0 until N) {
        for (x in 0 until M) {
            if (map[y][x] == -1) {
                repeat(4) {
                    val nx = x + dx[it]
                    val ny = y + dy[it]
                    if (nx in 0 until M && ny in 0 until N && map[ny][nx] != -1) {
                        set.add(map[ny][nx])
                    }
                }
                val sum = (set.sumOf { group[it] } + 1) % 10
                writer.write(sum.toString())
                set.clear()
            } else
                writer.write("0")
        }
        writer.newLine()
    }
    writer.flush()
}