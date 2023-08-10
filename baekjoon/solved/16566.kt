package `16566`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

fun update(a: IntArray, tree: IntArray, node: Int, start: Int, end: Int, idx: Int, value: Int) {
    if (idx < start || end < idx)
        return
    if (start == end) {
        a[idx] = value
        tree[node] = value
        return
    }
    val mid = (start + end) / 2
    update(a, tree, node * 2, start, mid, idx, value)
    update(a, tree, node * 2 + 1, mid + 1, end, idx, value)
    tree[node] = tree[node * 2] + tree[node * 2 + 1]
}

fun query(tree: IntArray, node: Int, start: Int, end: Int, left: Int, right: Int): Int {
    if (left > end || right < start)
        return 0
    if (left <= start && end <= right)
        return tree[node]
    val mid = (start + end) / 2
    val lsum = query(tree, node * 2, start, mid, left, right)
    val rsum = query(tree, node * 2 + 1, mid + 1, end, left, right)
    return lsum + rsum
}

// 세그먼트트리, 이분탐색
// 해당 구간에 사용할 수 있는 남은 카드가 몇개 있나?
// 이분탐색에서 mid 대소 비교와 남은 카드 비교를 같이 해서 판단
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val treeSize = 1 shl (ceil(log2(N.toDouble() + 1)).toInt() + 1)
    val tree = IntArray(treeSize) { 0 }
    val a = IntArray(N + 1) { 0 }
    reader.readLine().split(" ").map { it.toInt() }.forEach {
        update(a, tree, 1, 0, N, it, 1)
    }
    reader.readLine().split(" ").map { it.toInt() }.forEach {
        var left = it + 1
        var right = N
        while (left < right) {
            val mid = (left + right) / 2
            val cnt = query(tree, 1, 0, N, left, mid)
            if (cnt > 0)
                right = mid
            else left = mid + 1
        }
        val ans = left
        update(a, tree, 1, 0, N, ans, 0)
        writer.write("${ans}\n")
    }
    writer.flush()
}