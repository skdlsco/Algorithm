package `2252`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Int>>(N + 1) { ArrayList() }
    val inDegreeArr = Array<Int>(N + 1) { 0 }
    repeat(M) {
        val (s, e) = reader.readLine().split(" ").map { it.toInt() }
        graph[s].add(e)
        inDegreeArr[e]++
    }
    val queue: Queue<Int> = LinkedList()
    inDegreeArr.forEachIndexed { i, inDegree ->
        if (i != 0 && inDegree == 0)
            queue.add(i)
    }
    val result = ArrayList<Int>()
    while (queue.isNotEmpty()) {
        val cur = queue.remove()
        result.add(cur)
        for (next in graph[cur]) {
            inDegreeArr[next]--
            if (inDegreeArr[next] == 0)
                queue.add(next)
        }
    }
    writer.write(result.joinToString(" "))
    writer.flush()
}