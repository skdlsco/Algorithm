package `1966`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val t = reader.readLine().toInt()
    repeat(t) {
        val (N, target) = reader.readLine().split(" ").map { it.toInt() }
        val arr = reader.readLine().split(" ").map { it.toInt() }
        val queue = LinkedList<Pair<Int, Int>>()
        for (i in 0 until N) {
            queue.add(Pair(i, arr[i]))
        }
        var cnt = 0
        while (queue.isNotEmpty()) {
            val cur = queue.pop()
            if (queue.firstOrNull { cur.second < it.second } == null) {
                cnt++
                if (cur.first == target) {
                    writer.write("${cnt}\n")
                    return@repeat
                }
            } else
                queue.add(cur)
        }
    }
    writer.flush()
}