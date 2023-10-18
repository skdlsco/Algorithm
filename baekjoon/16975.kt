package `16975`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.ceil
import kotlin.math.log2

fun update(tree: Array<Long>, node: Int, left: Int, right: Int, start: Int, end: Int, value: Long) {
    if (right < start || left > end)
        return
    if (start <= left && right <= end) {
        tree[node] += value
        return
    }
    update(tree, node * 2, left, (left + right) / 2, start, end, value)
    update(tree, node * 2 + 1, (left + right) / 2 + 1, right, start, end, value)
}

fun query(tree: Array<Long>, node: Int, left: Int, right: Int, index: Int): Long {
    if (index !in left..right)
        return 0L
    if (left == right) {
        val ans = tree[node]
        tree[node] = 0L
        return ans
    }
    tree[node * 2] += tree[node]
    tree[node * 2 + 1] += tree[node]
    tree[node] = 0L
    return query(tree, node * 2, left, (left + right) / 2, index) +
            query(tree, node * 2 + 1, (left + right) / 2 + 1, right, index)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toLong() }.toTypedArray()
    val treeSize = 1 shl (ceil(log2(N.toDouble())).toInt() + 1)
    val tree = Array<Long>(treeSize + 1) { 0L }
    val M = reader.readLine().toInt()
    repeat(M) {
        val tokenizer = StringTokenizer(reader.readLine())
        val command = tokenizer.nextToken().toInt()
        if (command == 1) {
            val i = tokenizer.nextToken().toInt() - 1
            val j = tokenizer.nextToken().toInt() - 1
            val k = tokenizer.nextToken().toLong()
            update(tree, 1, 0, N - 1, i, j, k)
        } else {
            val i = tokenizer.nextToken().toInt() - 1
            val d = query(tree, 1, 0, N - 1, i)
            arr[i] += d
            writer.write("${arr[i]}\n")
        }
    }
    writer.flush()
}
    