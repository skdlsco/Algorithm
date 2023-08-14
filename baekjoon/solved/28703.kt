package `28703`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val pq = PriorityQueue<Long>()
    var last = reader.readLine().split(" ").map {
        val v = it.toLong()
        pq.add(v)
        v
    }.maxOf { it }
    var maxValue = last
    var ans = last - pq.first()
    while (pq.first() < (Int.MAX_VALUE.toLong() * 2)) {
        val first = pq.remove()
        ans = min(ans, maxValue - first)
        maxValue = max(maxValue, first * 2)
        pq.add(first * 2)
    }
    writer.write("${ans}\n")
    writer.flush()
}