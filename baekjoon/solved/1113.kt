package `1113`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Int>>(N + 2) { Array(M + 2) { 1 } }
    val maxHeightMap = Array<Array<Int>>(N + 2) { Array(M + 2) { 1 } }

    for (y in 1..N) {
        val line = reader.readLine().iterator()
        for (x in 1..M) {
            map[y][x] = line.nextChar().digitToInt()
        }
    }

    // 물을 2~9까지 채워본다.
    // map[i][j]가 물높이 보다 크거나 같거나 경우 벽으로 판정한다.
    // map 전체를 탐색한 후 외부와 연결이 안된 벽이 아닌 칸을 확인한다.
    // 해당칸에 대한 정보를 따로 저장한다.
    for (h in 2..9) {
        val visited = Array<Array<Boolean>>(N + 2) { Array(M + 2) { false } }
        val queue = LinkedList<Pair<Int, Int>>()
        queue.add(Pair(0, 0))
        visited[0][0] = true
        while (queue.isNotEmpty()) {
            val (cx, cy) = queue.pop()

            for (i in 0 until 4) {
                val nx = cx + dx[i]
                val ny = cy + dy[i]
                if (ny in 0 until N + 2 && nx in 0 until M + 2 && !
                    visited[ny][nx] && map[ny][nx] < h
                ) {
                    queue.add(Pair(nx, ny))
                    visited[ny][nx] = true
                }
            }
        }
        for (y in 1..N) {
            for (x in 1..M) {
                if (!visited[y][x] && map[y][x] < h)
                    maxHeightMap[y][x] = maxOf(maxHeightMap[y][x], h)
            }
        }
    }
    var sum = 0
    for (y in 1..N) {
        for (x in 1..M) {
            sum += maxOf(0, maxHeightMap[y][x] - map[y][x])
        }
    }
    writer.write("${sum}\n")
    writer.flush()
}