package `13334`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    val arr = Array<Pair<Int, Int>>(n) { Pair(0, 0) }
    repeat(n) {
        val (h, o) = reader.readLine().split(" ").map { it.toInt() }
        arr[it] = Pair(minOf(h, o), maxOf(h, o))
    }
    val d = reader.readLine().toInt()
    arr.sortWith() { o1, o2 ->
        if (o1.second == o2.second)
            o1.first - o2.first
        else
            o1.second - o2.second
    }
    var result = 0
    val pq = PriorityQueue<Int>()
    for ((s, e) in arr) {
        // 두군데 모두 철로에 포함되어야한다
        if (e - s > d)
            continue
        pq.add(s)
        while (pq.isNotEmpty() && pq.peek() < e - d)
            pq.remove()
        result = maxOf(result, pq.size)
    }
    writer.write("${result}\n")
    writer.flush()
}