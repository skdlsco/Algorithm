package `2533`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import kotlin.math.min

fun countEarlyAdaptor(graph: Array<LinkedList<Int>>, dp: Array<Array<Int>>, parent: Int, cur: Int) {
    dp[cur][1] = 1
    for (next in graph[cur]) {
        if (parent != next) {
            countEarlyAdaptor(graph, dp, cur, next)

            // 얼리어답터가 아닌겅우
            dp[cur][0] += dp[next][1]
            // 얼리어답터인 경우
            dp[cur][1] += min(dp[next][0], dp[next][1])
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val graph = Array<LinkedList<Int>>(N + 1) { LinkedList() }
    // dp[N][isEarlyAdapter] = node N이 얼리어탑터일때(아닐때) 최소 수
    val dp = Array<Array<Int>>(N + 1) { Array(2) { 0 } }
    repeat(N - 1) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }
    countEarlyAdaptor(graph, dp, 1, 1)
    println(min(dp[1][0], dp[1][1]))
}