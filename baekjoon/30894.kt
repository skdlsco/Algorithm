package `Main`

import java.util.LinkedList

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

val dx = arrayOf(1, 0, -1, 0)
val dy = arrayOf(0, 1, 0, -1)

data class Data(val x: Int, val y: Int, val time: Int)

fun main() {
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val (sy, sx, ey, ex) = reader.readLine().split(" ").map { it.toInt() - 1 }
    val map = Array<Array<Array<Boolean>>>(4) {
        Array(N) { Array(M) { true } }
    }
    val visited = Array<Array<Array<Boolean>>>(4) {
        Array(N) { Array(M) { false } }
    }
    val originMap = Array<Array<Char>>(N) { Array(M) { ' ' } }
    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until M) {
            originMap[y][x] = line[x]
        }
    }
    for (i in 0 until 4) {
        for (y in 0 until N) {
            for (x in 0 until M) {
                if (originMap[y][x] == '#') {
                    map[i][y][x] = false
                    continue
                }
                if (originMap[y][x] == '.')
                    continue
                val d = (i + originMap[y][x].digitToInt()) % 4
                var cy = y
                var cx = x
                map[i][cy][cx] = false
                cy += dy[d]
                cx += dx[d]
                while (cy in 0 until N &&
                        cx in 0 until M &&
                        originMap[cy][cx] == '.') {
                    map[i][cy][cx] = false
                    cy += dy[d]
                    cx += dx[d]
                }
            }
        }
    }
    var ans = -1
    val queue = LinkedList<Data>()
    queue.add(Data(sx, sy, 0))
    while (queue.isNotEmpty()) {
        val (cx, cy, time) = queue.pop()
        if (cx == ex && cy == ey) {
            ans = time
            break
        }
        if (map[(time + 1) % 4][cy][cx] && !visited[(time + 1) % 4][cy][cx]) {
            queue.add(Data(cx, cy, time + 1))
            visited[(time + 1) % 4][cy][cx] = true
        }
        for (i in 0 until 4) {
            val nx = cx + dx[i]
            val ny = cy + dy[i]
            val nt = time + 1
            if (ny in 0 until N && nx in 0 until M &&
                    map[nt % 4][ny][nx] && !visited[nt % 4][ny][nx]) {
                queue.add(Data(nx, ny, nt))
                visited[nt % 4][ny][nx] = true
            }
        }
    }
    if (ans == -1)
        writer.write("GG\n")
    else
        writer.write("${ans}\n")
    writer.flush()
}