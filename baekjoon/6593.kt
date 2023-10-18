package `6593`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

val dz = arrayOf(0, 0, 0, 0, 1, -1)
val dy = arrayOf(0, 0, 1, -1, 0, 0)
val dx = arrayOf(1, -1, 0, 0, 0, 0)

data class Point(val z: Int, val y: Int, val x: Int) {
    override fun equals(other: Any?): Boolean {
        if (other === this)
            return true
        if (other is Point)
            return other.z == this.z && other.y == this.y && other.x == this.x
        return false
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    while (true) {
        val (L, R, C) = reader.readLine().split(" ").map { it.toInt() }
        if (L == 0 && R == 0 && C == 0)
            break
        val map = Array<Array<Array<Int>>>(L) { Array(R) { Array(C) { 0 } } }
        val visited = Array<Array<Array<Boolean>>>(L) { Array(R) { Array(C) { false } } }
        var start = Point(0, 0, 0)
        var end = Point(0, 0, 0)
        for (z in 0 until L) {
            for (y in 0 until R) {
                val line = reader.readLine()
                for (x in 0 until C) {
                    when (line[x]) {
                        'S' -> start = Point(z, y, x)
                        'E' -> end = Point(z, y, x)
                        '#' -> map[z][y][x] = -1
                        else -> map[z][y][x] = 0
                    }
                }
            }
            reader.readLine()
        }
        var result: Int? = null
        val queue = LinkedList<Pair<Point, Int>>()
        queue.add(Pair(start, 0))
        visited[start.z][start.y][start.x] = true
        while (queue.isNotEmpty()) {
            val (now, distance) = queue.pop()
            if (now == end) {
                result = distance
                break
            }
            repeat(6) {
                val next = Point(now.z + dz[it], now.y + dy[it], now.x + dx[it])
                if (next.z in 0 until L && next.y in 0 until R && next.x in 0 until C &&
                    map[next.z][next.y][next.x] != -1 &&
                    !visited[next.z][next.y][next.x]
                ) {
                    queue.add(Pair(next, distance + 1))
                    visited[next.z][next.y][next.x] = true
                }
            }
        }
        if (result == null)
            println("Trapped!")
        else
            println("Escaped in ${result} minute(s).")
    }
}