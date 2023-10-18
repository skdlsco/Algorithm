package `28071`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val visited = Array<Boolean>(M * 300 + 1) { false }
    val arr = reader.readLine().split(" ").map { it.toInt() }
    visited[0] = true
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(0, 0))
    var result = 0
    while (queue.isNotEmpty()) {
        val (cur, cnt) = queue.pop()
        if (cur % K == 0)
            result = max(result, cur)
        if (cnt == M)
            continue
        for (i in 0 until N) {
            val next = arr[i] + cur
            if (!visited[next]) {
                queue.add(Pair(next, cnt + 1))
                visited[next] = true
            }
        }
    }
    writer.write("${result}\n")
    writer.flush()
}