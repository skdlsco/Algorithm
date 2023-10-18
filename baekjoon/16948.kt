package `16948`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

data class Data(val r: Int, val c: Int, val count: Int)

val dr = arrayOf(-2, -2, 0, 0, 2, 2)
val dc = arrayOf(-1, 1, -2, 2, -1, 1)
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val (sr, sc, er, ec) = reader.readLine().split(" ").map { it.toInt() }
    val visited = Array<Array<Boolean>>(N) { Array(N) { false } }
    val queue = LinkedList<Data>()
    visited[sr][sc] = true
    queue.add(Data(sr, sc, 0))
    var result = -1
    while (queue.isNotEmpty()) {
        val (r, c, cnt) = queue.pop()
        if (r == er && c == ec) {
            result = cnt
            break
        }
        for (i in 0 until 6) {
            val nextR = r + dr[i]
            val nextC = c + dc[i]
            if (nextR !in 0 until N || nextC !in 0 until N || visited[nextR][nextC])
                continue
            visited[nextR][nextC] = true
            queue.add(Data(nextR, nextC, cnt + 1))
        }
    }
    writer.write("${result}\n")
    writer.flush()
}