package `2357`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

fun init(tree: Array<Int>, arr: Array<Int>, node: Int, start: Int, end: Int, comp: (Int, Int) -> Int) {
    if (start == end) {
        tree[node] = arr[start]
        return
    }
    init(tree, arr, node * 2, start, (end + start) / 2, comp)
    init(tree, arr, node * 2 + 1, (end + start) / 2 + 1, end, comp)
    tree[node] = comp(tree[node * 2], tree[node * 2 + 1])
}

fun query(tree: Array<Int>, node: Int, start: Int, end: Int, left: Int, right: Int, default: Int, comp: (Int, Int) -> Int): Int {
    if (right < start || end < left)
        return default
    if (left <= start && end <= right)
        return tree[node]
    return comp(
            query(tree, node * 2, start, (start + end) / 2, left, right, default, comp),
            query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right, default, comp)
    )
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val treeSize = 1 shl ceil(log2(N.toDouble())).toInt() + 1
    val minTree = Array<Int>(treeSize) { Int.MAX_VALUE }
    val maxTree = Array<Int>(treeSize) { Int.MIN_VALUE }
    val arr = Array<Int>(N) { reader.readLine().toInt() }

    init(minTree, arr, 1, 0, N - 1) { o1, o2 -> if (o1 < o2) o1 else o2 }
    init(maxTree, arr, 1, 0, N - 1) { o1, o2 -> if (o1 > o2) o1 else o2 }
    repeat(M) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() - 1 }
        val min = query(minTree, 1, 0, N - 1, a, b, Int.MAX_VALUE) { o1, o2 -> if (o1 < o2) o1 else o2 }
        val max = query(maxTree, 1, 0, N - 1, a, b, Int.MIN_VALUE) { o1, o2 -> if (o1 > o2) o1 else o2 }
        writer.write("${min} ${max}\n")
        writer.flush()
    }
}
