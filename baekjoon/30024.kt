package `30024`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Data(val x: Int, val y: Int, val v: Int)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Array<Int>>(N) { Array(M) { 0 } }
    val pq = PriorityQueue<Data>() { o1, o2 ->
        o2.v - o1.v
    }
    val visited = Array<Array<Boolean>>(N) { Array(M) { false } }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until M) {
            if (y == 0 || x == 0 || y == N - 1 || x == M - 1) {
                pq.add(Data(x, y, row[x]))
                visited[y][x] = true
            }
            arr[y][x] = row[x]
        }
    }
    val K = reader.readLine().toInt()
    repeat(K) {
        val (x, y) = pq.remove()
        writer.write("${y + 1} ${x + 1}\n")
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (ny in 0 until N && nx in 0 until M && !visited[ny][nx]) {
                visited[ny][nx] = true
                pq.add(Data(nx, ny, arr[ny][nx]))
            }
        }
    }
    writer.flush()
}