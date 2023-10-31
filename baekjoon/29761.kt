import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayList
import java.util.LinkedList

data class Pos(val x: Int, val y: Int)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, X, sy, sx) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Int>>(N) { Array(N) { 0 } }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N) {
            map[y][x] = row[x]
        }
    }
    val queue = Array<LinkedList<Pair<Int, Pos>>>(100001) { LinkedList() }
    var ans = 0
    val visited = Array<Array<Boolean>>(N) { Array(N) { false } }
    val startPos = Pos(sx - 1, sy - 1)
    visited[startPos.y][startPos.x] = true
    queue[map[startPos.y][startPos.x]].add(Pair(X, startPos))
    for (i in 100000 downTo 0) {
        val curQueue = queue[i]
        while (curQueue.isNotEmpty()) {
            val (height, pos) = curQueue.remove()
            val (cx, cy) = pos
            ans++
            if (height <= 1)
                continue
            for (d in 0 until 4) {
                val nx = cx + dx[d]
                val ny = cy + dy[d]
                if (nx in 0 until N && ny in 0 until N && !visited[ny][nx]) {
                    if (map[ny][nx] == map[cy][cx]) {
                        visited[ny][nx] = true
                        queue[map[ny][nx]].add(Pair(height - 1, Pos(nx, ny)))
                    }
                    if (map[ny][nx] < map[cy][cx]) {
                        visited[ny][nx] = true
                        queue[map[ny][nx]].add(Pair(X, Pos(nx, ny)))
                    }
                }
            }
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}
