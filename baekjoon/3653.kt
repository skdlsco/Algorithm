package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

fun update(tree: Array<Int>, node: Int, s: Int, e: Int, idx: Int, v: Int) {
    if (idx < s || e < idx)
        return
    if (s == e) {
        tree[node] = v
    } else {
        update(tree, node * 2, s, (s + e) / 2, idx, v)
        update(tree, node * 2 + 1, (s + e) / 2 + 1, e, idx, v)
        tree[node] = tree[node * 2] + tree[node * 2 + 1]
    }
}

fun query(tree: Array<Int>, node: Int, s: Int, e: Int, left: Int, right: Int): Int {
    if (right < s || e < left)
        return 0
    if (left <= s && e <= right)
        return tree[node]
    return query(tree, node * 2, s, (s + e) / 2, left, right) +
            query(tree, node * 2 + 1, (s + e) / 2 + 1, e, left, right)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val (N, M) = reader.readLine().split(" ").map { it.toInt() }
        val treeSize = 1 shl (ceil(log2(N + M.toDouble())).toInt() + 1)
        val tree = Array<Int>(treeSize) { 0 }
        val arr = Array<Int>(N + 1) { N - it + 1 }
        for (i in 1..N) {
            update(tree, 1, 1, N + M, i, 1)
        }
        var idx = N + 1
        val input = reader.readLine().split(" ").map { it.toInt() }
        for (target in input) {
            val ans = query(tree, 1, 1, N + M, arr[target] + 1, N + M)
            update(tree, 1, 1, N + M, arr[target], 0)
            arr[target] = idx
            update(tree, 1, 1, N + M, idx, 1)
            writer.write("${ans} ")
            idx++
        }
        writer.newLine()
    }
    writer.flush()
}
    