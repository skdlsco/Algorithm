package `2042`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.ceil
import kotlin.math.log2

fun init(tree: Array<Long>, arr: Array<Long>, node: Int, start: Int, end: Int) {
    if (start == end) {
        tree[node] = arr[start]
        return
    }
    init(tree, arr, node * 2, start, (start + end) / 2)
    init(tree, arr, node * 2 + 1, (start + end) / 2 + 1, end)
    tree[node] = tree[node * 2] + tree[node * 2 + 1]
}

fun update(tree: Array<Long>, arr: Array<Long>, node: Int, start: Int, end: Int, idx: Int, v: Long) {
    if (idx < start || end < idx)
        return
    if (start == end) {
        arr[idx] = v
        tree[node] = v
        return
    }
    update(tree, arr, node * 2, start, (start + end) / 2, idx, v)
    update(tree, arr, node * 2 + 1, (start + end) / 2 + 1, end, idx, v)
    tree[node] = tree[node * 2] + tree[node * 2 + 1]
}

fun query(tree: Array<Long>, node: Int, start: Int, end: Int, left: Int, right: Int): Long {
    if (left > end || right < start)
        return 0L
    if (left <= start && end <= right)
        return tree[node]
    return query(tree, node * 2, start, (start + end) / 2, left, right) +
            query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right)
}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Long>(N) { reader.readLine().toLong() }
    val treeSize = 1 shl ceil(log2(N.toDouble())).toInt() + 1
    val tree = Array<Long>(treeSize) { 0 }
    init(tree, arr, 1, 0, N - 1)
    repeat(M + K) {
        val (a, b, c) = reader.readLine().split(" ").map { it.toLong() }
        if (a == 1L) {
            update(tree, arr, 1, 0, N - 1, b.toInt() - 1, c)
        } else {
            val ans = query(tree, 1, 0, N - 1, b.toInt() - 1, c.toInt() - 1)
            writer.write("${ans}\n")
        }
    }
    writer.flush()
}