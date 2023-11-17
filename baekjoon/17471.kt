package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import kotlin.math.abs

fun valid(N: Int, graph: Array<ArrayList<Int>>, group: ArrayList<Int>): Boolean {
    if (group.isEmpty())
        return true
    val visited = Array<Boolean>(N) { false }
    val queue = LinkedList<Int>()
    queue.add(group.first())
    visited[group.first()] = true
    while (queue.isNotEmpty()) {
        val cur = queue.remove()
        for (next in graph[cur]) {
            if (!visited[next] && group.contains(next)) {
                queue.add(next)
                visited[next] = true
            }
        }
    }
    return group.all { visited[it] }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val people = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Int>>(N) { ArrayList() }
    repeat(N) {
        val nodes = reader.readLine().split(" ").map { it.toInt() - 1 }
        for (i in 1 until nodes.size) {
            graph[it].add(nodes[i])
        }
    }
    var ans = Int.MAX_VALUE
    for (i in 0 until (1 shl N)) {
        // 1: A선거구, 0: B선거구
        val groupA = ArrayList<Int>()
        val groupB = ArrayList<Int>()
        for (bit in 0 until N) {
            if (i and (1 shl bit) > 0) {
                groupA.add(bit)
            } else {
                groupB.add(bit)
            }
        }
        if (!valid(N, graph, groupA) || !valid(N, graph, groupB))
            continue
        val groupASum = groupA.sumOf { people[it] }
        val groupBSum = groupB.sumOf { people[it] }
        val diff = abs(groupASum - groupBSum)
        ans = minOf(ans, diff)
    }
    if (ans == Int.MAX_VALUE)
        ans = -1
    writer.write("${ans}\n")
    writer.flush()
}
    