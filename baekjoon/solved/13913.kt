package `13913`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun addQueue(queue: LinkedList<Int>, path: Array<Int>, cur: Int, next: Int) {
    if (next in 0 until 200001 && path[next] == next) {
        path[next] = cur
        queue.add(next)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val path = Array<Int>(200001) { it }
    val queue = LinkedList<Int>()

    queue.add(N)
    while (queue.isNotEmpty()) {
        val cur = queue.pop()
        if (cur == K)
            break
        addQueue(queue, path, cur, cur - 1)
        addQueue(queue, path, cur, cur + 1)
        addQueue(queue, path, cur, cur * 2)
    }
    val tracking = ArrayList<Int>()
    var cur = K
    while (cur != N) {
        tracking.add(cur)
        cur = path[cur]
    }
    tracking.add(cur)
    writer.write("${tracking.size - 1}\n")
    writer.write("${tracking.reversed().joinToString(" ")}\n")
    writer.flush()
}