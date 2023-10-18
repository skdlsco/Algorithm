package `11505`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

const val MOD = 1000000007

fun init(arr: LongArray, tree: LongArray, node: Int, start: Int, end: Int) {
    if (start == end) {
        tree[node] = arr[start]
    } else {
        val mid = (start + end) / 2
        init(arr, tree, node * 2, start, mid)
        init(arr, tree, node * 2 + 1, mid + 1, end)
        tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD
    }
}

fun update(a: LongArray, tree: LongArray, node: Int, start: Int, end: Int, index: Int, value: Long) {
    if (index < start || index > end) {
        // 해당 노드가 update와 관련이 없는 경우
        return
    }
    if (start == end) {
        // leaf node 인경우
        a[index] = value
        tree[node] = value
        return
    }
    val mid = (start + end) / 2
    update(a, tree, node * 2, start, mid, index, value)
    update(a, tree, node * 2 + 1, mid + 1, end, index, value)
    tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD
}

fun query(tree: LongArray, node: Int, start: Int, end: Int, left: Int, right: Int): Long {
    if (start > right || end < left)
        return 1
    if (left <= start && end <= right)
        return tree[node]
    val mid = (start + end) / 2
    val lsum = query(tree, node * 2, start, mid, left, right)
    val rsum = query(tree, node * 2 + 1, mid + 1, end, left, right)
    return (lsum * rsum) % MOD
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val treeSize = 1 shl ceil(log2(N.toDouble())).toInt() + 1
    val arr = LongArray(N) { reader.readLine().toLong() }
    val tree = LongArray(treeSize) { 1 }
    init(arr, tree, 1, 0, N - 1)
    repeat(M + K) {
        val (q, a, b) = reader.readLine().split(" ").map { it.toInt() }
        if (q == 1) {
            update(arr, tree, 1, 0, N - 1, a - 1, b.toLong())
        } else {
            val left = minOf(a - 1, b - 1)
            val right = maxOf(a - 1, b - 1)
            writer.write("${query(tree, 1, 0, N - 1, left, right)}\n")
        }
    }
    writer.flush()
}