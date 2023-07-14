package `1948`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import kotlin.math.max

// 어떤 시작 도시로부터 도착 도시까지 출발을 하여 가능한 모든 경로를 탐색한다고 한다.
// 모든 경로이기 때문에 서로 같은 시간을 가진 최장경로
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val M = reader.readLine().toInt()
    val graph = Array<ArrayList<Pair<Int, Int>>>(N + 1) { ArrayList() }
    val revGraph = Array<ArrayList<Pair<Int, Int>>>(N + 1) { ArrayList() }
    val inDegreeArr = Array<Int>(N + 1) { 0 }

    repeat(M) {
        val (s, e, c) = reader.readLine().split(" ").map { it.toInt() }
        inDegreeArr[e]++
        graph[s].add(Pair(e, c))
        revGraph[e].add(Pair(s, c))
    }
    val costArr = Array<Int>(N + 1) { 0 }
    val queue = LinkedList<Pair<Int, Int>>()
    val (start, dest) = reader.readLine().split(" ").map { it.toInt() }
    queue.add(Pair(start, 0))
    // 최장거리구하기
    while (queue.isNotEmpty()) {
        val (node, cost) = queue.pop()

        for ((nn, nc) in graph[node]) {
            costArr[nn] = max(costArr[nn], nc + cost)
            inDegreeArr[nn]--
            if (inDegreeArr[nn] == 0)
                queue.add(Pair(nn, costArr[nn]))
        }
    }
    var cnt = 0
    val visited = Array<Boolean>(N + 1) { false }

    // 간선 수 세기
    queue.add(Pair(dest, costArr[dest]))
    visited[dest] = true
    while (queue.isNotEmpty()) {
        val (node, cost) = queue.pop()

        for ((nn, nc) in revGraph[node]) {
            if (costArr[nn] + nc == cost) {
                cnt++
                if (!visited[nn]) {
                    queue.add(Pair(nn, costArr[nn]))
                    visited[nn] = true
                }
            }
        }
    }
    writer.write("${costArr[dest]}\n")
    writer.write("${cnt}\n")
    writer.flush()
}