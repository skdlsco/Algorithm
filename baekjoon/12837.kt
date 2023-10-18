package `12837`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

fun update(tree: Array<Long>, node: Int, start: Int, end: Int, idx: Int, diff: Long) {
    if (end < idx || start > idx)
        return
    tree[node] += diff
    if (start != end) {
        val mid = (start + end) / 2
        update(tree, node * 2, start, mid, idx, diff)
        update(tree, node * 2 + 1, mid + 1, end, idx, diff)
    }
}

fun query(tree: Array<Long>, node: Int, start: Int, end: Int, left: Int, right: Int): Long {
    if (left <= start && end <= right)
        return tree[node]
    if (start > right || end < left)
        return 0L
    val mid = (start + end) / 2
    return query(tree, node * 2, start, mid, left, right) +
            query(tree, node * 2 + 1, mid + 1, end, left, right)
}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, Q) = reader.readLine().split(" ").map { it.toInt() }
    val treeHeight = 2 shl ceil(log2(N.toDouble())).toInt()
    val tree = Array<Long>(treeHeight) { 0 }

    repeat(Q) {
        val (c, a, b) = reader.readLine().split(" ").map { it.toInt() }
        if (c == 1) {
            update(tree, 1, 1, N, a, b.toLong())
        } else {
            val result = query(tree, 1, 1, N, a, b)
            writer.write("${result}\n")
        }
    }
    writer.flush()
}