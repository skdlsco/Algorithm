import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.*

val treeSize = 1 shl (ceil(log2(250001.0)).toInt() + 1)
val tree = Array<Long>(treeSize) { 0 }
val bit = Array<Long>(treeSize) { 0 }
val arr = Array<Long>(250001) { 0L }

fun init(node: Int, s: Int, e: Int, k: Long) {
    if (s == e) {
        bit[node] = arr[s]
        tree[node] = if (bit[node] == k) 1 else 0
    } else {
        init(node * 2, s, (s + e) / 2, k)
        init(node * 2 + 1, (s + e) / 2 + 1, e, k)
        bit[node] = bit[node * 2] and bit[node * 2 + 1]
        tree[node] = tree[node * 2] + tree[node * 2 + 1]
    }
}

fun update(node: Int, s: Int, e: Int, left: Int, right: Int, v: Long, k: Long) {
    if (right < s || e < left)
        return
    if (left <= s && e <= right) {
        if (bit[node] or v == bit[node])
            return
        if (s == e) {
            bit[node] = bit[node] or v
            tree[node] = if (bit[node] == k) 1 else 0
            return
        }
    }
    update(node * 2, s, (s + e) / 2, left, right, v, k)
    update(node * 2 + 1, (s + e) / 2 + 1, e, left, right, v, k)
    bit[node] = bit[node * 2] and bit[node * 2 + 1]
    tree[node] = tree[node * 2] + tree[node * 2 + 1]
}

fun query(node: Int, s: Int, e: Int, left: Int, right: Int): Long {
    if (right < s || e < left)
        return 0L
    if (left <= s && e <= right)
        return tree[node]
    return query(node * 2, s, (s + e) / 2, left, right) +
            query(node * 2 + 1, (s + e) / 2 + 1, e, left, right)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }

    reader.readLine().split(" ").map { it.toLong() }.withIndex().forEach {
        arr[it.index + 1] = it.value
    }
    init(1, 1, N, K.toLong())
    val M = reader.readLine().toInt()
    repeat(M) {
        val input = StringTokenizer(reader.readLine())
        val command = input.nextToken().toInt()
        if (command == 1) {
            val a = input.nextToken().toInt()
            val b = input.nextToken().toInt()
            val c = input.nextToken().toLong()
            update(1, 1, N, a, b, c, K.toLong())
        } else {
            val a = input.nextToken().toInt()
            val b = input.nextToken().toInt()
            writer.write("${query(1, 1, N, a, b)}\n")
        }
    }
    writer.flush()
}