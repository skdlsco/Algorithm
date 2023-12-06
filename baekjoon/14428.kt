import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack
import java.util.StringTokenizer
import kotlin.math.*


val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

val arr = Array<Int>(300000) { Int.MAX_VALUE }
val tree = Array<Int>(300000) { 0 }
var N = 0

fun calc(node: Int) {
    if (arr[tree[node * 2]] > arr[tree[node * 2 + 1]]) {
        tree[node] = tree[node * 2 + 1]
    } else {
        tree[node] = tree[node * 2]
    }
}

fun init(node: Int, s: Int, e: Int) {
    if (s == e) {
        tree[node] = s
    } else {
        init(node * 2, s, (s + e) / 2)
        init(node * 2 + 1, (s + e) / 2 + 1, e)
        calc(node)
    }
}

fun update(node: Int, s: Int, e: Int, idx: Int, v: Int) {
    if (idx < s || e < idx)
        return
    if (s == e) {
        arr[s] = v
        return
    }
    update(node * 2, s, (s + e) / 2, idx, v)
    update(node * 2 + 1, (s + e) / 2 + 1, e, idx, v)
    calc(node)
}

fun query(node: Int, s: Int, e: Int, left: Int, right: Int): Int {
    if (right < s || e < left)
        return 0
    if (left <= s && e <= right)
        return tree[node]
    val lResult = query(node * 2, s, (s + e) / 2, left, right)
    val rResult = query(node * 2 + 1, (s + e) / 2 + 1, e, left, right)
    if (arr[lResult] > arr[rResult])
        return rResult
    else return lResult
}

fun main() {
    N = reader.readLine().toInt()
    reader.readLine().split(" ").map { it.toInt() }.withIndex().forEach {
        arr[it.index + 1] = it.value
    }
    init(1, 1, N)
    val M = reader.readLine().toInt()
    repeat(M) {
        val (a, b, c) = reader.readLine().split(" ").map { it.toInt() }
        if (a == 1) {
            update(1, 1, N, b, c)
        } else {
            writer.write("${query(1, 1, N, b, c)}\n")
        }
    }
    writer.flush()
}