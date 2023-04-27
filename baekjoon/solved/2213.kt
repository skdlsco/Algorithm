package `2213`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import kotlin.math.max

fun solve(weights: Array<Int>, graph: Array<LinkedList<Int>>, dp: Array<Array<Int>>, prev: Int, node: Int) {
    for (next in graph[node]) {
        if (next == prev)
            continue
        solve(weights, graph, dp, node, next)
    }
    val sum = arrayOf(0, 0)
    for (next in graph[node]) {
        if (next == prev)
            continue
        sum[1] += max(dp[1][next], dp[0][next])
        sum[0] += dp[0][next]
    }
    dp[1][node] = sum[0] + weights[node]
    dp[0][node] = sum[1]
}

fun makeSet(
    graph: Array<LinkedList<Int>>,
    dp: Array<Array<Int>>,
    prev: Int,
    node: Int,
    set: MutableSet<Int>,
    isSkip: Boolean
) {
    var isNextSkip = false
    if (!isSkip && dp[1][node] > dp[0][node]) {
        isNextSkip = true
        set.add(node)
    }
    for (next in graph[node]) {
        if (next == prev)
            continue
        makeSet(graph, dp, node, next, set, isNextSkip)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    val weightArr = Array<Int>(n + 1) { 0 }
    val graph = Array<LinkedList<Int>>(n + 1) { LinkedList() }

    reader.readLine().split(" ").map { it.toInt() }.forEachIndexed { index, i ->
        weightArr[index + 1] = i
    }
    repeat(n - 1) {
        val (s, e) = reader.readLine().split(" ").map { it.toInt() }
        graph[s].add(e)
        graph[e].add(s)
    }
    val dp = Array<Array<Int>>(2) { Array(n + 1) { 0 } }
    // dp[2][i] 현재 노드까지 최대 독립집합의 크기(0: 현재 노드 미포함, 1: 현재 노드 포함)
    solve(weightArr, graph, dp, 0, 1)
    writer.write("${max(dp[0][1], dp[1][1])}\n")
    val set = HashSet<Int>()
    makeSet(graph, dp, 0, 1, set, false)
    writer.write("${set.sorted().joinToString(" ")}\n")
    writer.flush()
}