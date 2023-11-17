import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

fun query(tree: Array<Long>, node: Int, start: Int, end: Int, left: Int, right: Int): Long {
    if (right < start || left > end)
        return 0
    if (left <= start && end <= right)
        return tree[node]
    val lSum = query(tree, node * 2, start, (start + end) / 2, left, right)
    val rSum = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right)
    return lSum + rSum
}

fun update(tree: Array<Long>, node: Int, start: Int, end: Int, idx: Int, diff: Long) {
    if (idx < start || end < idx)
        return
    if (start == end) {
        tree[node] += diff
    } else {
        update(tree, node * 2, start, (start + end) / 2, idx, diff)
        update(tree, node * 2 + 1, (start + end) / 2 + 1, end, idx, diff)
        tree[node] = tree[node * 2] + tree[node * 2 + 1]
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, S) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ")
        .mapIndexed { index, s -> Pair(s.toInt(), index) }
        .sortedBy { it.first }
        .toTypedArray()
    var rank = 0
    var prev = 0
    var cnt = 0
    for (i in 0 until N) {
        val (cur, originIdx) = arr[i]
        if (prev != cur) {
            rank += cnt + 1
            cnt = 0
        } else {
            cnt++
        }
        arr[i] = Pair(rank, originIdx)
        prev = cur
    }
    arr.sortBy { it.second }

    val treeSize = 1 shl (ceil(log2(N + 1.0)) + 1).toInt()
    val tree = Array<Long>(treeSize) { 0 }
    var ans = 0L
    var sum = 0L
    for (i in 0 until S) {
        val (v, idx) = arr[i]
        sum += query(tree, 1, 0, N, 0, v - 1)
        update(tree, 1, 0, N, v, 1)
    }
    ans = maxOf(ans, sum)
    for (i in S until N) {
        val (v, idx) = arr[i]
        sum -= query(tree, 1, 0, N, arr[i - S].first + 1, N)
        update(tree, 1, 0, N, arr[i - S].first, -1)
        sum += query(tree, 1, 0, N, 0, v - 1)
        update(tree, 1, 0, N, v, 1)
        ans = maxOf(ans, sum)
    }
    writer.write("${ans}\n")
    writer.flush()
}