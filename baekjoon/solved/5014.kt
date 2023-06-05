package `5014`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (F, S, G, U, D) = reader.readLine().split(" ").map { it.toInt() }
    val visited = Array<Boolean>(F + 1) { false }
    val queue = LinkedList<Pair<Int, Int>>()

    visited[S] = true
    queue.add(Pair(0, S))
    var result = -1
    while (queue.isNotEmpty()) {
        val (cur, pos) = queue.pop()

        if (pos == G) {
            result = cur
            break
        }
        for (next in arrayOf(pos + U, pos - D)) {
            if (next in 1..F && !visited[next]) {
                visited[next] = true
                queue.add(Pair(cur + 1, next))
            }
        }
    }
    if (result == -1)
        writer.write("use the stairs")
    else
        writer.write("${result}")
    writer.flush()
}