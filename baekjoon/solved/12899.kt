package `12899`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun update(arr: IntArray, tree: IntArray, node: Int, start: Int, end: Int, index: Int, value: Int) {
    if (index < start || index > end)
        return
    if (start == end) {
        tree[node] += value
        return
    }
    val mid = (start + end) / 2
    update(arr, tree, node * 2, start, mid, index, value)
    update(arr, tree, node * 2 + 1, mid + 1, end, index, value)
    tree[node] += value
}

fun query(tree: IntArray, node: Int, start: Int, end: Int, rank: Int): Int {
    if (start == end)
        return start
    val mid = (start + end) / 2
    val lNode = node * 2
    val rNode = node * 2 + 1
    if (tree[lNode] >= rank)
        return query(tree, lNode, start, mid, rank)
    return query(tree, rNode, mid + 1, end, rank - tree[lNode])
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()

    val arrSize = 2000001
    val treeSize = 1 shl 22
    val arr = IntArray(arrSize)
    val tree = IntArray(treeSize)

    repeat(N) {
        val (query, value) = reader.readLine().split(" ").map { it.toInt() }

        if (query == 1) {
            // add
            update(arr, tree, 1, 0, arrSize - 1, value, 1)
        } else {
            val num = query(tree, 1, 0, arrSize - 1, value)
            update(arr, tree, 1, 0, arrSize - 1, num, -1)
            writer.write("${num}\n")
            // remove
        }
    }
    writer.flush()
}