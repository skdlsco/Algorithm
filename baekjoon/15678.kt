package `15678`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, D) = reader.readLine().split(" ").map { it.toInt() }
    val pq = PriorityQueue<Pair<Long, Int>>() { o1, o2 ->
        if (o1.first > o2.first)
            -1
        else if (o1.first < o2.first)
            1
        else 0
    }
    val tokenizer = StringTokenizer(reader.readLine())
    var result = tokenizer.nextToken().toLong()
    pq.add(Pair(result, 0))
    for (i in 1 until N) {
        val cur = tokenizer.nextToken().toLong()
        while (pq.peek().second < i - D)
            pq.remove()
        val top = pq.peek()
        val point = max(top.first + cur, cur)
        pq.add(Pair(point, i))
        result = max(result, point)
    }
    writer.write("${result}\n")
    writer.flush()
}