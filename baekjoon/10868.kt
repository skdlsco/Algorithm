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
        tree[node] = minOf(tree[node * 2], tree[node * 2 + 1])
    }
}

fun query(tree: Array<Int>, node: Int, s: Int, e: Int, left: Int, right: Int): Int {
    if (right < s || e < left)
        return Int.MAX_VALUE
    if (left <= s && e <= right)
        return tree[node]
    return minOf(query(tree, node * 2, s, (s + e) / 2, left, right),
            query(tree, node * 2 + 1, (s + e) / 2 + 1, e, left, right))
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val treeSize = 1 shl (ceil(log2(N + 1.0)).toInt() + 1)
    val tree = Array<Int>(treeSize) { 0 }
    for (i in 1..N) {
        val v = reader.readLine().toInt()
        update(tree, 1, 1, N, i, v)
    }
    repeat(M) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        writer.write("${query(tree, 1, 1, N, a, b)}\n")
    }
    writer.newLine()
    writer.flush()
}
    