package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

fun calcNode(n1: Pair<Int, Int>, n2: Pair<Int, Int>) = Pair(
        minOf(n1.first, n2.first),
        maxOf(n1.second, n2.second)
)

fun init(tree: Array<Pair<Int, Int>>, node: Int, s: Int, e: Int, arr: Array<Int>) {
    if (s == e) {
        tree[node] = Pair(arr[s], arr[s])
    } else {
        init(tree, node * 2, s, (s + e) / 2, arr)
        init(tree, node * 2 + 1, (s + e) / 2 + 1, e, arr)
        tree[node] = calcNode(tree[node * 2], tree[node * 2 + 1])
    }
}

fun update(tree: Array<Pair<Int, Int>>, arr: Array<Int>, node: Int, s: Int, e: Int, idx: Int, v: Int) {
    if (e < idx || idx < s)
        return
    if (s == e) {
        arr[idx] = v
        tree[node] = Pair(v, v)
    } else {
        update(tree, arr, node * 2, s, (s + e) / 2, idx, v)
        update(tree, arr, node * 2 + 1, (s + e) / 2 + 1, e, idx, v)
        tree[node] = calcNode(tree[node * 2], tree[node * 2 + 1])
    }
}

fun query(tree: Array<Pair<Int, Int>>, node: Int, s: Int, e: Int, left: Int, right: Int): Pair<Int, Int> {
    if (e < left || right < s)
        return Pair(Int.MAX_VALUE, Int.MIN_VALUE)
    if (left <= s && e <= right) {
        return tree[node]
    }
    return calcNode(query(tree, node * 2, s, (s + e) / 2, left, right),
            query(tree, node * 2 + 1, (s + e) / 2 + 1, e, left, right))
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val (N, K) = reader.readLine().split(" ").map { it.toInt() }
        val treeSize = 1 shl (ceil(log2(N.toDouble())).toInt() + 1)
        val arr = Array<Int>(N) { it }
        val tree = Array<Pair<Int, Int>>(treeSize) { Pair(Int.MAX_VALUE, Int.MIN_VALUE) }
        init(tree, 1, 0, N - 1, arr)
        repeat(K) {
            val (c, a, b) = reader.readLine().split(" ").map { it.toInt() }
            if (c == 0) {
                val tempA = arr[a]
                val tempB = arr[b]
                update(tree, arr, 1, 0, N - 1, a, tempB)
                update(tree, arr, 1, 0, N - 1, b, tempA)
            } else {
                val result = query(tree, 1, 0, N - 1, a, b)
                if (result.first == a && result.second == b)
                    writer.write("YES\n")
                else
                    writer.write("NO\n")
            }
        }
    }
    writer.flush()
}
    