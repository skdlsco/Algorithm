package `16234`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import kotlin.math.abs

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, L, R) = reader.readLine().split(" ").map { it.toInt() }
    var map = Array<Array<Int>>(N) { Array(N) { 0 } }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N) {
            map[y][x] = row[x]
        }
    }
    var isMoved = true
    var cnt = 0
    while (isMoved) {
        isMoved = false
        val visited = Array<Array<Boolean>>(N) { Array(N) { false } }
        val group = ArrayList<Pair<Int, Int>>()
        val nextMap = Array<Array<Int>>(N) { Array(N) { 0 } }
        for (y in 0 until N) {
            for (x in 0 until N) {
                if (visited[y][x])
                    continue
                var sum = map[y][x]
                val queue = LinkedList<Pair<Int, Int>>()
                visited[y][x] = true
                group.clear()
                group.add(Pair(x, y))
                queue.add(Pair(x, y))
                while (queue.isNotEmpty()) {
                    val (cx, cy) = queue.pop()
                    for (i in 0 until 4) {
                        val nx = cx + dx[i]
                        val ny = cy + dy[i]
                        if (nx !in 0 until N || ny !in 0 until N)
                            continue
                        if (visited[ny][nx])
                            continue
                        val diff = abs(map[ny][nx] - map[cy][cx])
                        if (diff in L..R) {
                            group.add(Pair(nx, ny))
                            queue.add(Pair(nx, ny))
                            sum += map[ny][nx]
                            visited[ny][nx] = true
                        }
                    }
                }
                val cell = sum / group.size
                group.forEach {
                    nextMap[it.second][it.first] = cell
                }
                if (group.size > 1)
                    isMoved = true
            }
        }
        map = nextMap
        cnt++
    }
    writer.write("${cnt - 1}\n")
    writer.flush()
}