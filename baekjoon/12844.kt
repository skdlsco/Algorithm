import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.*

val treeSize = 1 shl (ceil(log2(500001.0)).toInt() + 1)
val tree = Array<Long>(treeSize) { 0 }
val lazy = Array<Long>(treeSize) { 0 }
val arr = Array<Long>(500001) { 0L }

fun init(node: Int, s: Int, e: Int) {
    if (s == e) {
        tree[node] = arr[s]
    } else {
        init(node * 2, s, (s + e) / 2)
        init(node * 2 + 1, (s + e) / 2 + 1, e)
        tree[node] = tree[node * 2] xor tree[node * 2 + 1]
    }
}

fun propagation(node: Int, s: Int, e: Int) {
    if (lazy[node] == 0L)
        return
    if ((e - s + 1) % 2 == 1)
        tree[node] = tree[node] xor lazy[node]
    if (s != e) {
        lazy[node * 2] = lazy[node * 2] xor lazy[node]
        lazy[node * 2 + 1] = lazy[node * 2 + 1] xor lazy[node]
    }
    lazy[node] = 0
}

fun update(node: Int, s: Int, e: Int, left: Int, right: Int, v: Long) {
    propagation(node, s, e)
    if (right < s || e < left)
        return
    if (left <= s && e <= right) {
        lazy[node] = lazy[node] xor v
        propagation(node, s, e)
        return
    }
    update(node * 2, s, (s + e) / 2, left, right, v)
    update(node * 2 + 1, (s + e) / 2 + 1, e, left, right, v)
    tree[node] = tree[node * 2] xor tree[node * 2 + 1]
}

fun query(node: Int, s: Int, e: Int, left: Int, right: Int): Long {
    propagation(node, s, e)
    if (right < s || e < left)
        return 0L
    if (left <= s && e <= right)
        return tree[node]
    return query(node * 2, s, (s + e) / 2, left, right) xor
            query(node * 2 + 1, (s + e) / 2 + 1, e, left, right)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()

    reader.readLine().split(" ").map { it.toLong() }.withIndex().forEach {
        arr[it.index] = it.value
    }
    init(1, 0, N - 1)
    val M = reader.readLine().toInt()
    repeat(M) {
        val input = StringTokenizer(reader.readLine())
        val command = input.nextToken().toInt()
        if (command == 1) {
            val a = input.nextToken().toInt()
            val b = input.nextToken().toInt()
            val c = input.nextToken().toLong()
            update(1, 0, N - 1, a, b, c)
        } else {
            val a = input.nextToken().toInt()
            val b = input.nextToken().toInt()
            writer.write("${query(1, 0, N - 1, a, b)}\n")
        }
    }
    writer.flush()
}