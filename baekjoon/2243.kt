package `2243`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

fun update(tree: IntArray, node: Int, start: Int, end: Int, index: Int, value: Int) {
    if (index < start || index > end) {
        // 해당 노드가 update와 관련이 없는 경우
        return
    }
    if (start == end) {
        // leaf node 인경우
        tree[node] += value
        return
    }
    val mid = (start + end) / 2
    update(tree, node * 2, start, mid, index, value)
    update(tree, node * 2 + 1, mid + 1, end, index, value)
    tree[node] = tree[node] + value
}


fun getTasteByRank(tree: IntArray, node: Int, start: Int, end: Int, rank: Int): Int {
    // 사탕 발견
    if (start == end)
        return start
    val mid = (start + end) / 2
    val lNode = node * 2
    val rNode = node * 2 + 1
    if (tree[lNode] >= rank)
        return getTasteByRank(tree, lNode, start, mid, rank)
    return getTasteByRank(tree, rNode, mid + 1, end, rank - tree[lNode])
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.`out`))
    val tasteSize = 1000001
    val n = reader.readLine().toInt()
    val treeSize = 1 shl ceil(log2(tasteSize.toDouble())).toInt() + 1
    val tree = IntArray(treeSize) { 0 }
    repeat(n) {
        val input = reader.readLine().split(" ").map { it.toInt() }
        if (input[0] == 1) {
            val taste = getTasteByRank(tree, 1, 0, tasteSize - 1, input[1])
            writer.write("${taste}\n")
            update(tree, 1, 0, tasteSize - 1, taste, -1)
        } else {
            update(tree, 1, 0, tasteSize - 1, input[1], input[2])
        }
    }
    writer.flush()
    writer.close()
}


