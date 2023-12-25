import java.io.*
import java.util.*
import kotlin.collections.ArrayDeque

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

data class Data(val y: Int, val x: Int, val level: Int)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun main() {
    val (H, W) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Boolean>>(H + 2) { Array(W + 2) { false } }

    for (y in 1..H) {
        val input = StringTokenizer(reader.readLine())
        for (x in 1..W) {
            map[y][x] = input.nextToken().toInt() == 1
        }
    }
    val cnt = Array<Int>(10001) { 0 }
    val queue = ArrayDeque<Data>()
    val visited = Array<Array<Boolean>>(H + 2) { Array(W + 2) { false } }
    visited[0][0] = true
    queue.addFirst(Data(0, 0, 0))
    while (queue.isNotEmpty()) {
        val (y, x, l) = queue.removeFirst()
        if (map[y][x])
            cnt[l]++
        for (i in 0 until 4) {
            val ny = y + dy[i]
            val nx = x + dx[i]
            if (ny in 0..H + 1 && nx in 0..W + 1 && !visited[ny][nx]) {
                visited[ny][nx] = true
                if (map[ny][nx])
                    queue.addLast(Data(ny, nx, l + 1))
                else
                    queue.addFirst(Data(ny, nx, l))
            }
        }
    }
    var level = 1
    while (cnt[level + 1] != 0) {
        level++
    }
    writer.write("${level}\n${cnt[level]}")
    writer.flush()
}