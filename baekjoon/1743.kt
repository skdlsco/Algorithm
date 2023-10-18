package `1743`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import kotlin.math.max

data class Point(val x: Int, val y: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Boolean>>(N) { Array(M) { false } }
    val dx = arrayOf(0, 0, 1, -1)
    val dy = arrayOf(1, -1, 0, 0)
    val arr = Array<Point>(K) { Point(0, 0) }
    repeat(K) {
        val (y, x) = reader.readLine().split(" ").map { it.toInt() - 1 }
        arr[it] = Point(x, y)
        map[y][x] = true
    }
    var result = 0

    for (p in arr) {
        if (!map[p.y][p.x])
            continue
        var cnt = 1
        val queue = LinkedList<Point>()
        queue.add(p)
        map[p.y][p.x] = false
        while (queue.isNotEmpty()) {
            val now = queue.pop()
            repeat(4) {
                val nextX = now.x + dx[it]
                val nextY = now.y + dy[it]
                if (nextX in 0 until M && nextY in 0 until N && map[nextY][nextX]) {
                    cnt++
                    queue.add(Point(nextX, nextY))
                    map[nextY][nextX] = false
                }
            }
        }
        result = max(result, cnt)
    }
    println(result)
}