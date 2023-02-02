package `13975`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()

    repeat(T) {
        val N = reader.readLine().toInt()
        val queue = PriorityQueue<Long> { a, b ->
            if (a > b)
                1
            else if (a == b)
                0
            else -1
        }
        reader.readLine().split(" ").forEach { queue.add(it.toLong()) }

        var sum = 0L
        while (queue.size > 1) {
            val a = queue.remove()
            val b = queue.remove()
            sum += a + b
            queue.add(a + b)
        }
        writer.write("$sum\n")
    }
    writer.flush()
}