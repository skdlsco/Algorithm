package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.log2

val MOD = 1_000_000_007

fun getTreeSize(N: Int): Int {
    return 1 shl (ceil(log2(N.toDouble())).toInt() + 1)
}

fun update(tree: Array<Long>, node: Int, s: Int, e: Int, idx: Int, diff: Long) {
    if (idx < s || e < idx)
        return
    if (s == e) {
        tree[node] = tree[node] + diff
    } else {
        update(tree, node * 2, s, (s + e) / 2, idx, diff)
        update(tree, node * 2 + 1, (s + e) / 2 + 1, e, idx, diff)
        tree[node] = tree[node * 2] + tree[node * 2 + 1]
    }
}

fun query(tree: Array<Long>, node: Int, s: Int, e: Int, left: Int, right: Int): Long {
    if (s > right || e < left)
        return 0L
    if (left <= s && e <= right)
        return tree[node]
    return query(tree, node * 2, s, (s + e) / 2, left, right) +
            query(tree, node * 2 + 1, (s + e) / 2 + 1, e, left, right)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val distTree = Array<Long>(getTreeSize(200001)) { 0L }
    val cntTree = Array<Long>(getTreeSize(200001)) { 0L }
    var ans = 1L
    repeat(N) {
        val x = reader.readLine().toInt()
        var sum = 0L
        val lCnt = query(cntTree, 1, 0, 200000, 0, x - 1)
        val lSum = query(distTree, 1, 0, 200000, 0, x - 1)
        sum += abs((x * lCnt) - lSum)
        val rCnt = query(cntTree, 1, 0, 200000, x + 1, 200000)
        val rSum = query(distTree, 1, 0, 200000, x + 1, 200000)
        sum += abs((x * rCnt) - rSum)
        sum %= MOD
        if (it > 0)
            ans = (ans * sum) % MOD
        update(cntTree, 1, 0, 200000, x, 1)
        update(distTree, 1, 0, 200000, x, x.toLong())
    }
    writer.write("${ans}\n")
    writer.flush()
}
    