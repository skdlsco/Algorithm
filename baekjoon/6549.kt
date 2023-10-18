package `6549`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.max

data class Data(val height: Long, val idx: Int)

fun f(left: Data, right: Data): Data =
    if (left.height < right.height)
        left
    else
        right

fun init(arr: Array<Data>, tree: Array<Data>, node: Int, start: Int, end: Int) {
    if (start == end) {
        tree[node] = arr[start]
    } else {
        init(arr, tree, node * 2, start, (start + end) / 2)
        init(arr, tree, node * 2 + 1, (start + end) / 2 + 1, end)
        tree[node] = f(tree[node * 2], tree[node * 2 + 1])
    }
}

fun query(tree: Array<Data>, node: Int, start: Int, end: Int, left: Int, right: Int): Data {
    if (left > end || right < start)
        return Data(Long.MAX_VALUE, Int.MAX_VALUE)
    if (left <= start && end <= right)
        return tree[node]
    val lsum = query(tree, node * 2, start, (start + end) / 2, left, right)
    val rsum = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right)
    return f(lsum, rsum)
}

fun getMax(arr: Array<Data>, tree: Array<Data>, start: Int, end: Int): Long {
    if (start > end)
        return 0
    if (start == end)
        return arr[start].height
    val width = (end - start) + 1
    val data = query(tree, 1, 0, arr.size - 1, start, end)
    val area = data.height * width
    val lData = getMax(arr, tree, start, data.idx - 1)
    val rData = getMax(arr, tree, data.idx + 1, end)
    return max(max(area, lData), rData)
}


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val input = reader.readLine().split(" ").map { it.toLong() }
        val N = input[0]
        if (input[0] == 0L)
            break
        val arr = Array(N.toInt()) {
            Data(input[it + 1], it)
        }
        val treeSize = 1 shl ceil(log2(N.toDouble())).toInt() + 1
        val tree = Array(treeSize) { Data(0, 0) }

        init(arr, tree, 1, 0, (N - 1).toInt())
        val result = getMax(arr, tree, 0, (N - 1).toInt())
        writer.write("${result}\n")
    }
    writer.flush()
}