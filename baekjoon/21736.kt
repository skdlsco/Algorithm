package `21736`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun dfs(map: Array<Array<Char>>, visited: Array<Array<Boolean>>, x: Int, y: Int): Int {
    var sum = 0
    if (map[y][x] == 'P')
        sum++
    for (i in 0 until 4) {
        val nx = x + dx[i]
        val ny = y + dy[i]
        if (ny in map.indices && nx in map[ny].indices && !visited[ny][nx] && map[ny][nx] != 'X') {
            visited[ny][nx] = true
            sum += dfs(map, visited, nx, ny)
        }
    }
    return sum
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Char>>(N) { Array(M) { ' ' } }
    val visited = Array<Array<Boolean>>(N) { Array(M) { false } }
    var sx = 0
    var sy = 0
    for (y in 0 until N) {
        val row = reader.readLine()
        for (x in 0 until M) {
            if (row[x] == 'I') {
                sx = x
                sy = y
            }
            map[y][x] = row[x]
        }
    }
    visited[sy][sx] = true
    val result = dfs(map, visited, sx, sy)
    if (result == 0)
        writer.write("TT\n")
    else
        writer.write("${result}\n")
    writer.flush()
}