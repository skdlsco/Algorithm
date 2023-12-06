package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val dy = arrayOf(0, 1, 0, -1)
val dx = arrayOf(1, 0, -1, 0)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val ans = ArrayList<Int>()
    val map = Array<Array<Int>>(N) { Array(M) { 0 } }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until M) {
            map[y][x] = row[x]
        }
    }
    for (i in 0 until N) {
        val visited = Array<Array<Array<Boolean>>>(4) { Array(N) { Array(M) { false } } }

        visited[0][i][0] = true
        var y = i
        var x = 0
        var d = 0
        while (true) {
            val nd = (d + 1) % 4
            val ny = y + map[y][x] * dy[d]
            val nx = x + map[y][x] * dx[d]
            if (ny !in 0 until N || nx !in 0 until M)
                break
            if (visited[nd][ny][nx]) {
                ans.add(i + 1)
                break
            }
            visited[nd][ny][nx] = true
            y = ny
            x = nx
            d = nd
        }
    }
    writer.write("${ans.size}\n")
    writer.write(ans.joinToString(" "))
    writer.flush()
}
    