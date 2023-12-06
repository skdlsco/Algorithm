package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

fun update(tree: Array<Int>, node: Int, s: Int, e: Int, left: Int, right: Int, v: Int) {
    if (e < left || right < s)
        return
    if (left <= s && e <= right) {
        tree[node] += v
        return
    }
    update(tree, node * 2, s, (s + e) / 2, left, right, v)
    update(tree, node * 2 + 1, (s + e) / 2 + 1, e, left, right, v)
}

fun query(tree: Array<Int>, node: Int, s: Int, e: Int, idx: Int): Int {
    if (idx !in s..e)
        return 0
    if (s == e) {
        val ans = tree[node]
        tree[node] = 0
        return ans
    }
    tree[node * 2] += tree[node]
    tree[node * 2 + 1] += tree[node]
    tree[node] = 0
    return query(tree, node * 2, s, (s + e) / 2, idx) +
            query(tree, node * 2 + 1, (s + e) / 2 + 1, e, idx)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val treeSize = 1 shl (ceil(log2(100001.0)).toInt() + 1)
    val tree = Array<Int>(treeSize) { 0 }
    repeat(N) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        val ans = query(tree, 1, 1, 100000, a) + query(tree, 1, 1, 100000, b)
        writer.write("${ans}\n")
        if (a + 1 != b) {
            update(tree, 1, 1, 100000, a + 1, b - 1, 1)
        }
    }
    writer.flush()
}
    