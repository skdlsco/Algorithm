package `D`

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
    val (h, w) = reader.readLine().split(" ").map { it.toInt() }
    val waterMap = Array<Array<Int>>(N + 1) { Array(M + 1) { 1 } }
    val map = Array<Array<Int>>(N + 1) { Array(M + 1) { 0 } }
    for (y in 1..N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 1..M) {
            map[y][x] = row[x - 1]
        }
    }
    val queue = LinkedList<Pair<Int, Int>>()
    val K = reader.readLine().toInt()
    repeat(K) {
        val (y, x) = reader.readLine().split(" ").map { it.toInt() }
        queue.add(Pair(x, y))
        waterMap[y][x] = 0
    }
    while (queue.isNotEmpty()) {
        val (x, y) = queue.remove()
        for (d in 0 until 4) {
            val nx = dx[d] + x
            val ny = dy[d] + y
            if (nx in 1..M && ny in 1..N) {
                if (map[ny][nx] >= map[y][x] && waterMap[ny][nx] != 0) {
                    waterMap[ny][nx] = 0
                    queue.add(Pair(nx, ny))
                }
            }
        }
    }
    val prefixSum = Array<Array<Int>>(N + 1) { Array(M + 1) { 0 } }
    for (y in 1..N) {
        for (x in 1..M) {
            prefixSum[y][x] = prefixSum[y][x - 1] +
                    prefixSum[y - 1][x] -
                    prefixSum[y - 1][x - 1] + if (waterMap[y][x] > 0) 1 else 0
        }
    }
    var ans = 0
    for (y in h..N) {
        for (x in w..M) {
            val temp = prefixSum[y][x] - prefixSum[y - h][x] - prefixSum[y][x - w] + prefixSum[y - h][x - w]
            if (temp == 0)
                ans++
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}
    