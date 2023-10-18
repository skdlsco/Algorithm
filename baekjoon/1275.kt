package `1275`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.max
import kotlin.math.min

// tree의 node, arr 범위 start, end
fun init(tree: Array<Long>, arr: Array<Long>, node: Int, start: Int, end: Int) {
    if (start == end) {
        tree[node] = arr[start]
        return
    }
    val mid = (start + end) / 2
    init(tree, arr, node * 2, start, mid)
    init(tree, arr, node * 2 + 1, mid + 1, end)
    tree[node] = tree[node * 2] + tree[node * 2 + 1]
}

fun update(tree: Array<Long>, arr: Array<Long>, node: Int, start: Int, end: Int, index: Int, value: Long) {
    if (start > index || end < index)
        return
    if (start == end) {
        arr[index] = value
        tree[node] = value
        return
    }
    val mid = (start + end) / 2
    update(tree, arr, node * 2, start, mid, index, value)
    update(tree, arr, node * 2 + 1, mid + 1, end, index, value)
    tree[node] = tree[node * 2] + tree[node * 2 + 1]
}

// start - end -> 현재 조회중인 트리의 범위
// left - right -> 쿼리의 범위
fun query(tree: Array<Long>, arr: Array<Long>, node: Int, start: Int, end: Int, left: Int, right: Int): Long {
    if (end < left || right < start)
        return 0L
    if (left <= start && end <= right)
        return tree[node]
    val mid = (start + end) / 2
    val leftSum = query(tree, arr, node * 2, start, mid, left, right)
    val rightSum = query(tree, arr, node * 2 + 1, mid + 1, end, left, right)
    return leftSum + rightSum
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, Q) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toLong() }.toTypedArray()
    val treeSize = 1 shl (ceil(log2(N.toDouble())).toInt() + 1)
    val tree = Array<Long>(treeSize) { 0 }

    init(tree, arr, 1, 0, N - 1)
    repeat(Q) {
        val (x, y, a, b) = reader.readLine().split(" ").map { it.toInt() }
        val result = query(tree, arr, 1, 0, N - 1, min(x, y) - 1, max(x, y) - 1)
        update(tree, arr, 1, 0, N - 1, a - 1, b.toLong())
        writer.write("$result\n")
    }
    writer.flush()
}
