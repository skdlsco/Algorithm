package `28066`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val deque = ArrayDeque<Int>()
    deque.addAll(1..N)
    while (deque.size >= K) {
        deque.addLast(deque.removeFirst())
        for (i in 1 until K) {
            deque.removeFirst()
        }
    }
    writer.write("${deque.removeFirst()}")
    writer.flush()
}