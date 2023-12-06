import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.*

val treeSize = 1 shl (ceil(log2(1000001.0)).toInt() + 1)
val tree = Array<Long>(treeSize) { 0 }
val lazy = Array<Long>(treeSize) { 0 }
val arr = Array<Long>(1000001) { 0L }

fun init(node: Int, s: Int, e: Int) {
    if (s == e) {
        tree[node] = arr[s]
    } else {
        init(node * 2, s, (s + e) / 2)
        init(node * 2 + 1, (s + e) / 2 + 1, e)
        tree[node] = tree[node * 2] + tree[node * 2 + 1]
    }
}

fun propagation(node: Int, s: Int, e: Int) {
    if (lazy[node] == 0L)
        return
    tree[node] += lazy[node] * (e - s + 1)
    if (s != e) {
        lazy[node * 2] += lazy[node]
        lazy[node * 2 + 1] += lazy[node]
    }
    lazy[node] = 0L
}

fun update(node: Int, s: Int, e: Int, left: Int, right: Int, v: Long) {
    propagation(node, s, e)
    if (right < s || e < left)
        return
    if (left <= s && e <= right) {
        tree[node] += (e - s + 1) * v
        if (s != e) {
            lazy[node * 2] += v
            lazy[node * 2 + 1] += v
        }
    } else {
        update(node * 2, s, (s + e) / 2, left, right, v)
        update(node * 2 + 1, (s + e) / 2 + 1, e, left, right, v)
        tree[node] = tree[node * 2] + tree[node * 2 + 1]
    }
}

fun query(node: Int, s: Int, e: Int, left: Int, right: Int): Long {
    propagation(node, s, e)
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
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }

    repeat(N) {
        val a = reader.readLine().toLong()
        arr[it + 1] = a
    }
    init(1, 1, N)
    repeat(M + K) {
        val input = StringTokenizer(reader.readLine())
        val command = input.nextToken().toInt()
        if (command == 1) {
            val a = input.nextToken().toInt()
            val b = input.nextToken().toInt()
            val c = input.nextToken().toLong()
            update(1, 1, N, a, b, c)
        } else {
            val a = input.nextToken().toInt()
            val b = input.nextToken().toInt()
            writer.write("${query(1, 1, N, a, b)}\n")
        }
    }
    writer.flush()
}