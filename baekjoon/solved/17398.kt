package `17398`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val set = Array<Int>(100001) { it }
val cnt = Array<Int>(100001) { 1 }

fun union(a: Int, b: Int) {
    val aRoot = find(a)
    val bRoot = find(b)
    if (aRoot == bRoot)
        return
    cnt[aRoot] = cnt[aRoot] + cnt[bRoot]
    set[bRoot] = set[aRoot]
}

fun getCnt(node: Int): Int {
    val root = find(node)
    return cnt[root]
}

fun find(node: Int): Int {
    if (set[node] != node)
        set[node] = find(set[node])
    return set[node]
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, Q) = reader.readLine().split(" ").map { it.toInt() }
    val edges = Array<Pair<Int, Int>>(M + 1) { Pair(0, 0) }
    val deleteSequence = ArrayList<Int>()
    val delete = Array<Boolean>(M + 1) { false }

    for (i in 1..M) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        edges[i] = Pair(a, b)
    }

    for (i in 1..Q) {
        val edgeIdx = reader.readLine().toInt()
        deleteSequence.add(edgeIdx)
        delete[edgeIdx] = true
    }

    for (i in 1..M) {
        if (!delete[i])
            union(edges[i].first, edges[i].second)
    }
    var sum = 0L
    for (delAt in deleteSequence.reversed()) {
        val (a, b) = edges[delAt]
        if (find(a) == find(b))
            continue
        sum += getCnt(a) * getCnt(b)
        union(a, b)
    }
    writer.write("${sum}\n")
    writer.flush()
}