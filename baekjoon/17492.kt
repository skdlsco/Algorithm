package `17492`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val dx = arrayOf(0, 1, 1, 1, 0, -1, -1, -1)
val dy = arrayOf(1, 1, 0, -1, -1, -1, 0, 1)

data class Point(var x: Int, var y: Int)

fun dfs(map: Array<Array<Int>>, points: ArrayList<Point>, depth: Int): Boolean {
    if (points.size - 1 == depth)
        return true
    for (p in points) {
        if (p.x == -1)
            continue
        val (x, y) = p
        for (i in 0 until 8) {
            val tx = x + dx[i]
            val ty = y + dy[i]
            val target = points.find { it.x == tx && it.y == ty } ?: continue
            val nx = tx + dx[i]
            val ny = ty + dy[i]
            if (map[ny][nx] == 1 || points.any { it.x == nx && it.y == ny })
                continue
            target.x = -2
            target.y = -2
            p.x = nx
            p.y = ny
            if (dfs(map, points, depth + 1))
                return true
            target.x = tx
            target.y = ty
            p.x = x
            p.y = y
        }
    }
    return false
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val map = Array<Array<Int>>(N) { Array(N) { 0 } }
    val points = ArrayList<Point>()
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N) {
            map[y][x] = row[x]
            if (row[x] == 2) {
                points.add(Point(x, y))
            }
        }
    }
    val result = dfs(map, points, 0)
    if (result)
        writer.write("Possible")
    else
        writer.write("Impossible")
    writer.flush()
}
