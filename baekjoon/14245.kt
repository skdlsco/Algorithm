import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.*

fun update(lazy: Array<Long>, node: Int, s: Int, e: Int, left: Int, right: Int, v: Long) {
    if (right < s || e < left)
        return
    if (left <= s && e <= right) {
        lazy[node] = lazy[node] xor v
    } else {
        update(lazy, node * 2, s, (s + e) / 2, left, right, v)
        update(lazy, node * 2 + 1, (s + e) / 2 + 1, e, left, right, v)
    }
}

fun query(lazy: Array<Long>, arr: Array<Long>, node: Int, s: Int, e: Int, idx: Int) {
    if (idx < s || e < idx)
        return
    if (s == e) {
        arr[idx] = arr[idx] xor lazy[node]
        lazy[node] = 0L
    } else {
        lazy[node * 2] = lazy[node * 2] xor lazy[node]
        lazy[node * 2 + 1] = lazy[node * 2 + 1] xor lazy[node]
        lazy[node] = 0L
        query(lazy, arr, node * 2, s, (s + e) / 2, idx)
        query(lazy, arr, node * 2 + 1, (s + e) / 2 + 1, e, idx)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val treeSize = 1 shl (ceil(log2(N + 1.0)).toInt() + 1)
    val lazy = Array<Long>(treeSize) { 0 }
    val arr = reader.readLine().split(" ").map { it.toLong() }.toTypedArray()
    val M = reader.readLine().toInt()
    repeat(M) {
        val input = StringTokenizer(reader.readLine())
        val command = input.nextToken().toInt()
        if (command == 1) {
            val l = input.nextToken().toInt()
            val r = input.nextToken().toInt()
            val v = input.nextToken().toLong()
            update(lazy, 1, 0, N - 1, l, r, v)
        } else {
            val idx = input.nextToken().toInt()
            query(lazy, arr, 1, 0, N - 1, idx)
            writer.write("${arr[idx]}\n")
        }
    }
    writer.flush()
}