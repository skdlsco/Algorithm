package `2164`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    val queue = LinkedList<Int>()
    (1..n).forEach { queue.add(it) }
    var f = true
    while (queue.size > 1) {
        if (f) {
            queue.pop()
        } else {
            queue.add(queue.pop())
        }
        f = !f
    }
    writer.write("${queue.pop()}\n")
    writer.flush()
}