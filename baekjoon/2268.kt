package `2268`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.max
import kotlin.math.min

fun update(tree: LongArray, array: LongArray, node: Int, start: Int, end: Int, index: Int, value: Long) {
    if (index !in start..end)
        return
    if (start == end) {
        array[start] = value
        tree[node] = value
        return
    }
    val mid = (start + end) / 2
    update(tree, array, node * 2, start, mid, index, value)
    update(tree, array, node * 2 + 1, mid + 1, end, index, value)
    tree[node] = tree[node * 2] + tree[node * 2 + 1]
}

fun query(tree: LongArray, array: LongArray, node: Int, start: Int, end: Int, left: Int, right: Int): Long {
    if (end < left || start > right)
        return 0L
    if (left <= start && end <= right)
        return tree[node]
    val mid = (start + end) / 2
    val leftSum = query(tree, array, node * 2, start, mid, left, right)
    val rightSum = query(tree, array, node * 2 + 1, mid + 1, end, left, right)
    return leftSum + rightSum
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val treeSize = 1 shl (ceil(log2(N.toDouble())).toInt() + 1)
    val array = LongArray(N)
    val tree = LongArray(treeSize)
    repeat(M) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        val command = stringTokenizer.nextToken().toInt()
        val a = stringTokenizer.nextToken().toInt() - 1
        val b = stringTokenizer.nextToken().toInt() - 1
        when (command) {
            0 -> {
                val result = query(tree, array, 1, 0, N - 1, min(a, b), max(a, b))
                writer.write("${result}\n")
            }

            1 -> {
                update(tree, array, 1, 0, N - 1, a, b.toLong() + 1)
            }
        }
    }
    writer.flush()
}