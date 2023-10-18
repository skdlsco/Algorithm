package `1005`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.max
import java.util.LinkedList
import java.util.Queue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()

    repeat(T) {
        val (N, K) = reader.readLine().split(" ").map { it.toInt() }
        val cost: Array<Int> = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
        val graph = Array<Queue<Int>>(N) { LinkedList<Int>() }
        val inDegreeArr = Array<Int>(N) { 0 }
        // input
        repeat(K) {
            val (start, end) = reader.readLine().split(" ").map { it.toInt() - 1 }
            graph[start].add(end)
            inDegreeArr[end]++
        }
        // 목적지
        val destination = reader.readLine().toInt() - 1
        // 현재 진입 차수가 0인 node를 담는다.
        val queue: Queue<Int> = LinkedList()

        // dp[i]
        // i node로 가는 비용
        val dp = Array<Int>(N) { 0 }

        // 시작(진입 차수가 0인 node) node 선택
        inDegreeArr.forEachIndexed { i, inDegree ->
            if (inDegree == 0) {
                dp[i] = cost[i]
                queue.add(i)
            }
        }
        while (queue.isNotEmpty()) {
            val node = queue.remove()
            if (node == destination)
                break
            for (end in graph[node]) {
                // 이전 건물이 완성되어야 하므로 max
                dp[end] = max(dp[end], dp[node] + cost[end])
                inDegreeArr[end]--
                if (inDegreeArr[end] == 0)
                    queue.add(end)
            }
        }
        writer.write("${dp[destination]}\n")
    }
    writer.flush()
}