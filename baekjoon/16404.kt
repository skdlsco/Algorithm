import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.*

fun ett(graph: Array<ArrayList<Int>>, range: Array<Pair<Int, Int>>, cur: Int, node: Int): Int {
    range[cur] = Pair(node, node)
    var max = node
    for (next in graph[cur]) {
        max = ett(graph, range, next, max + 1)
    }
    range[cur] = Pair(node, max)
    return max
}

fun propagation(tree: Array<Long>, lazy: Array<Long>, node: Int, s: Int, e: Int) {
    tree[node] += lazy[node] * (e - s + 1)
    if (s != e) {
        lazy[node * 2] += lazy[node]
        lazy[node * 2 + 1] += lazy[node]
    }
    lazy[node] = 0
}

fun update(tree: Array<Long>, node: Int, s: Int, e: Int, left: Int, right: Int, v: Long) {
    if (right < s || e < left)
        return
    if (left <= s && e <= right) {
        tree[node] += v
        return
    }
    update(tree, node * 2, s, (s + e) / 2, left, right, v)
    update(tree, node * 2 + 1, (s + e) / 2 + 1, e, left, right, v)
}

fun query(tree: Array<Long>, node: Int, s: Int, e: Int, idx: Int): Long {
    if (idx !in s..e)
        return 0L
    if (s == e) {
        val ans = tree[node]
        tree[node] = 0L
        return ans
    }
    tree[node * 2] += tree[node]
    tree[node * 2 + 1] += tree[node]
    tree[node] = 0L
    return query(tree, node * 2, s, (s + e) / 2, idx) +
            query(tree, node * 2 + 1, (s + e) / 2 + 1, e, idx)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val parents = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Int>>(N + 1) { ArrayList() }
    // node[i] = i의 구간
    val node = Array<Pair<Int, Int>>(N + 1) { Pair(it, it) }
    val arr = Array<Long>(N + 1) { 0L }
    var root = -1
    // init graph
    for (i in 1..N) {
        val p = parents[i - 1]
        if (p == -1)
            root = i
        else
            graph[p].add(i)
    }
    ett(graph, node, root, 1)
    val treeHeight = 1 shl (ceil(log2(N + 1.0)).toInt() + 1)
    val tree = Array<Long>(treeHeight) { 0 }
    repeat(M) {
        val input = reader.readLine().split(" ").map { it.toInt() }
        if (input[0] == 1) {
            val (left, right) = node[input[1]]
            val v = input[2].toLong()
            update(tree, 1, 1, N, left, right, v)
        } else {
            val idx = node[input[1]].first
            val diff = query(tree, 1, 1, N, idx)
            arr[idx] += diff
            val ans = arr[idx]
            writer.write("${ans}\n")
        }
    }
    writer.flush()
}