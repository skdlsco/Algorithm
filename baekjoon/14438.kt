import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayList
import java.util.Stack
import java.util.StringTokenizer
import java.util.TreeMap
import java.util.TreeSet
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.sinh

fun init(tree: Array<Int>, arr: Array<Int>, node: Int, start: Int, end: Int) {
    if (start == end) {
        tree[node] = arr[start]
    } else {
        init(tree, arr, node * 2, start, (end + start) / 2)
        init(tree, arr, node * 2 + 1, (end + start) / 2 + 1, end)
        tree[node] = minOf(tree[node * 2], tree[node * 2 + 1])
    }
}

fun update(tree: Array<Int>, arr: Array<Int>, node: Int, start: Int, end: Int, index: Int) {
    if (index < start || end < index)
        return
    if (start == end) {
        tree[node] = arr[index]
    } else {
        update(tree, arr, node * 2, start, (end + start) / 2, index)
        update(tree, arr, node * 2 + 1, (end + start) / 2 + 1, end, index)
        tree[node] = minOf(tree[node * 2], tree[node * 2 + 1])
    }
}

fun query(tree: Array<Int>, node: Int, start: Int, end: Int, left: Int, right: Int): Int {
    if (right < start || end < left)
        return Int.MAX_VALUE
    if (left <= start && end <= right)
        return tree[node]
    val l = query(tree, node * 2, start, (start + end) / 2, left, right)
    val r = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right)
    return minOf(l, r)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    val M = reader.readLine().toInt()
    val treeHeight = 1 shl (ceil(log2(N.toDouble())).toInt() + 1)
    val tree = Array<Int>(treeHeight + 1) { Int.MAX_VALUE }
    init(tree, arr, 1, 0, N - 1)
    repeat(M) {
        val input = reader.readLine().split(" ").map { it.toInt() }
        if (input[0] == 1) {
            arr[input[1] - 1] = input[2]
            update(tree, arr, 1, 0, N - 1, input[1] - 1)
        } else {
            val ans = query(tree, 1, 0, N - 1, input[1] - 1, input[2] - 1)
            writer.write("${ans}\n")
        }
    }
    writer.flush()
}
