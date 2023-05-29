package `28069`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val visited = Array<Boolean>(1000001) { false }

    val queue = LinkedList<Pair<Int, Int>>()

    queue.add(Pair(0, 0))
    visited[0] = true
    // 0 + 0 / 2 = 0, 1 + 1 / 2 = 1
    // K번 이내에 도착만 할 수 있으면 정확히 K번에 도착할 수 있다.
    while (queue.isNotEmpty()) {
        val (pos, time) = queue.pop()
        if (time == K)
            continue
        for (np in arrayOf(pos + 1, pos + pos / 2)) {
            if (np <= N && !visited[np]) {
                queue.add(Pair(np, time + 1))
                visited[np] = true
            }
        }
    }
    writer.write(if (visited[N]) "minigimbob" else "water")
    writer.flush()
}