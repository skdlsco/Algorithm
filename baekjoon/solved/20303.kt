package `20303`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min


// 인원, 사탕 수
val info = Array<Pair<Int, Int>>(30001) { Pair(0, 0) }
val set = Array<Int>(30001) { it }
val dp = Array<Int>(3001) { 0 }

fun union(a: Int, b: Int) {
    val aRoot = findRoot(a)
    val bRoot = findRoot(b)
    if (aRoot == bRoot)
        return
    info[aRoot] = Pair(info[aRoot].first + info[bRoot].first, info[aRoot].second + info[bRoot].second)
    set[bRoot] = aRoot
}

fun findRoot(node: Int): Int {
    if (set[node] == node)
        return node
    val root = findRoot(set[node])
    set[node] = root
    return root
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val tokenizer = StringTokenizer(reader.readLine())
    for (i in 1..N) {
        info[i] = Pair(1, tokenizer.nextToken().toInt())
    }
    repeat(M) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        union(a, b)
    }
    val arr = ArrayList<Pair<Int, Int>>()
    for (i in 1..N) {
        if (findRoot(i) == i)
            arr.add(info[i])
    }
    for (data in arr) {
        for (i in K downTo data.first) {
            dp[i] = max(dp[i], dp[i - data.first] + data.second)
        }
    }
    val ans = (0 until K).maxOf { dp[it] }
    writer.write("${ans}\n")
    writer.flush()
}