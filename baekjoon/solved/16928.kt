package `16928`
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val visited = Array<Boolean>(106) { false }
    val board = Array<Int>(101) { it }
    val nextQueue = LinkedList<Int>()
    val queue = LinkedList<Int>()
    visited[1] = true
    repeat(N + M) {
        val (start, end) = reader.readLine().split(" ").map { it.toInt() }
        board[start] = end
    }
    nextQueue.push(1)
    var cnt = 0
    while (nextQueue.isNotEmpty()) {
        queue.clear()
        queue.addAll(nextQueue)
        nextQueue.clear()
        while (queue.isNotEmpty()) {
            val now = queue.pop()
            (1..6).forEach { dice ->
                val next = now + dice
                if (next <= 100 && !visited[next]) {
                    nextQueue.push(board[next])
                    visited[next] = true
                }
            }
        }
        cnt++
        if (nextQueue.find { it == 100 } != null)
            break
    }
    println(cnt)
}