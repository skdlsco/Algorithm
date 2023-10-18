package `2623`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Int>>(N + 1) { ArrayList() }
    val inDegreeArr = Array<Int>(N + 1) { 0 }
    repeat(M) {
        val arr = reader.readLine().split(" ").map { it.toInt() }
        var u = arr[1]
        for (i in 2 until arr.size) {
            val v = arr[i]
            graph[u].add(v)
            inDegreeArr[v]++
            u = v
        }
    }
    val queue = LinkedList<Int>()
    for (i in 1..N) {
        if (inDegreeArr[i] == 0)
            queue.add(i)
    }
    val ans = ArrayList<Int>()
    while (queue.isNotEmpty()) {
        val cur = queue.remove()
        ans.add(cur)
        for (next in graph[cur]) {
            inDegreeArr[next]--
            if (inDegreeArr[next] == 0)
                queue.add(next)
        }
    }
    if (ans.size != N)
        writer.write("0")
    else
        writer.write(ans.joinToString(" "))
    writer.flush()
}