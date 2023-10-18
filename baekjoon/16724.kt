package `16724`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun dfs(map: Array<Array<Int>>, visited: Array<Array<Int>>, y: Int, x: Int, n: Int): Boolean {
    visited[y][x] = n
    val d = map[y][x]
    val ny = dy[d] + y
    val nx = dx[d] + x
    if (visited[ny][nx] == -1) {
        return dfs(map, visited, ny, nx, n)
    } else
        return visited[ny][nx] == n
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val visited = Array<Array<Int>>(N) { Array(M) { -1 } }
    val map = Array<Array<Int>>(N) { Array(M) { 0 } }
    for (y in 0 until N) {
        val row = reader.readLine()
        for (x in 0 until M) {
            map[y][x] = "DURL".indexOf(row[x])
        }
    }
    var cnt = 0
    var n = 0
    for (y in 0 until N) {
        for (x in 0 until M) {
            if (visited[y][x] == -1) {
                cnt += if (dfs(map, visited, y, x, n)) 1 else 0
                n++
            }
        }
    }
    writer.write("${cnt}\n")
    writer.flush()
}
    