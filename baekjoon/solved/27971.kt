package `27971`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M, A, B) = reader.readLine().split(" ").map { it.toInt() }
    val visited = Array<Boolean>(N + 1) { false }
    repeat(M) {
        val (L, R) = reader.readLine().split(" ").map { it.toInt() }
        for (i in L..R)
            visited[i] = true
    }
    // cur, depth
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(0, 0))
    visited[0] = true
    val dx = arrayOf(A, B)
    while (queue.isNotEmpty()) {
        val (cur, depth) = queue.pop()
        if (cur == N) {
            println(depth)
            return
        }
        for (i in 0 until 2) {
            val next = cur + dx[i]
            if (next in 0..N && !visited[next]) {
                queue.add(Pair(next, depth + 1))
                visited[next] = true
            }
        }
    }
    println(-1)
}