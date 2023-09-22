package `10090`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

fun update(tree: LongArray, node: Int, start: Int, end: Int, index: Int, value: Long) {
    if (index < start || index > end) {
        // 해당 노드가 update와 관련이 없는 경우
        return
    }
    if (start == end) {
        // leaf node 인경우
        tree[node] = value
        return
    }
    val mid = (start + end) / 2
    update(tree, node * 2, start, mid, index, value)
    update(tree, node * 2 + 1, mid + 1, end, index, value)
    tree[node] = tree[node * 2] + tree[node * 2 + 1]
}

fun query(tree: LongArray, node: Int, start: Int, end: Int, left: Int, right: Int): Long {
    if (left > end || right < start) {
        // 계산 구간에 포함되지 않는 경우
        return 0
    }
    // 구간 전체가 계산 구간에 포함된 경우 아래까지 내려갈 필요가 없다.
    if (left <= start && end <= right) {
        return tree[node]
    }
    val mid = (start + end) / 2
    val lsum = query(tree, node * 2, start, mid, left, right)
    val rsum = query(tree, node * 2 + 1, mid + 1, end, left, right)
    return lsum + rsum
}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    val treeSize = 1 shl ceil(log2(n.toDouble())).toInt() + 1
    val tree = LongArray(treeSize) { 0 }
    val arr = reader.readLine().split(" ").map { it.toInt() }
    var sum = 0L
    for (cur in arr) {
        val cnt = query(tree, 1, 0, n - 1, 0, cur)
        update(tree, 1, 0, n - 1, cur, 1L)
        sum += cur - cnt - 1
    }
    writer.write("${sum}\n")
    writer.flush()
}
    