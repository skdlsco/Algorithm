package `1516`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val baseBuildTimeArr = Array<Int>(N) { 0 }
    val graph = Array<LinkedList<Int>>(N) { LinkedList() }
    val inDegreeArr = Array<Int>(N) { 0 }

    for (i in 0 until N) {
        val input = reader.readLine().split(" ").map { it.toInt() }
        baseBuildTimeArr[i] = input[0]
        input.slice(1 until input.lastIndex).forEach {
            graph[it - 1].add(i)
            inDegreeArr[i]++
        }
    }

    val realBuildTimeArr = Array<Int>(N) { 0 }
    val queue = LinkedList<Int>()
    for (i in 0 until N) {
        if (inDegreeArr[i] == 0) {
            realBuildTimeArr[i] = baseBuildTimeArr[i]
            queue.add(i)
        }
    }
    while (queue.isNotEmpty()) {
        val cur = queue.pop()
        for (next in graph[cur]) {
            realBuildTimeArr[next] = max(realBuildTimeArr[next], realBuildTimeArr[cur])
            inDegreeArr[next]--
            if (inDegreeArr[next] == 0) {
                realBuildTimeArr[next] += baseBuildTimeArr[next]
                queue.add(next)
            }
        }
    }
    writer.write(realBuildTimeArr.joinToString("\n"))
    writer.flush()
}