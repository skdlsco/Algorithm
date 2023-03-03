package `14442`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

data class Info(val x: Int, val y: Int, val k: Int, val depth: Int)

fun bfs(N: Int, M: Int, K: Int, map: Array<Array<Boolean>>, visited: Array<Array<Array<Boolean>>>): Int {
    val queue = LinkedList<Info>()
    queue.add(Info(0, 0, 0, 1))
    visited[0][0][0] = true

    while (queue.isNotEmpty()) {
        val now = queue.pop()

        if (now.y == N - 1 && now.x == M - 1)
            return now.depth
        for (i in 0 until 4) {
            val nextX = now.x + dx[i]
            val nextY = now.y + dy[i]
            val nextK = now.k + 1
            if (nextY in 0 until N && nextX in 0 until M) {
                if (!map[nextY][nextX] && !visited[now.k][nextY][nextX]) {
                    queue.add(Info(nextX, nextY, now.k, now.depth + 1))
                    visited[now.k][nextY][nextX] = true
                }
                if (map[nextY][nextX] && nextK in 0..K && !visited[nextK][nextY][nextX]) {
                    queue.add(Info(nextX, nextY, nextK, now.depth + 1))
                    visited[nextK][nextY][nextX] = true
                }
            }
        }
    }
    return -1
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    // true -> 못지나감
    val map = Array<Array<Boolean>>(N) { Array(M) { false } }
    val visited = Array<Array<Array<Boolean>>>(K + 1) { Array(N) { Array(M) { false } } }

    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until M) {
            map[y][x] = line[x] == '1'
        }
    }

    val result = bfs(N, M, K, map, visited)
    println(result)
}