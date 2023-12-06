import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.*

fun eulerTourTechnique(graph: Array<ArrayList<Int>>, mapping: Array<Pair<Int, Int>>, cur: Int, startIdx: Int): Int {
    var endIdx = startIdx
    for (next in graph[cur]) {
        endIdx = eulerTourTechnique(graph, mapping, next, endIdx + 1)
    }
    mapping[cur] = Pair(startIdx, endIdx)
    return endIdx
}

fun update(tree: Array<Long>, node: Int, s: Int, e: Int, idx: Int, v: Long) {
    if (idx < s || e < idx)
        return
    tree[node] += v
    if (s == e) {
        return
    } else {
        update(tree, node * 2, s, (s + e) / 2, idx, v)
        update(tree, node * 2 + 1, (s + e) / 2 + 1, e, idx, v)
    }
}

fun query(tree: Array<Long>, node: Int, s: Int, e: Int, left: Int, right: Int): Long {
    if (right < s || e < left)
        return 0L
    if (left <= s && e <= right) {
        return tree[node]
    } else {
        return query(tree, node * 2, s, (s + e) / 2, left, right) +
                query(tree, node * 2 + 1, (s + e) / 2 + 1, e, left, right)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Int>>(N + 1) { ArrayList() }
    var root = 0
    reader.readLine().split(" ").map { it.toInt() }.withIndex().forEach {
        if (it.value == -1) {
            root = it.index + 1
        } else {
            graph[it.value].add(it.index + 1)
        }
    }
    val mapping = Array<Pair<Int, Int>>(N + 1) { Pair(0, 0) }
    eulerTourTechnique(graph, mapping, root, 1)
    val treeSize = 1 shl (ceil(log2(N + 1.0)).toInt() + 1)
    val tree = Array<Long>(treeSize) { 0L }
    repeat(M) {
        val input = StringTokenizer(reader.readLine())
        val command = input.nextToken().toInt()
        if (command == 1) {
            val target = input.nextToken().toInt()
            val v = input.nextToken().toLong()
            val (l, r) = mapping[target]
            update(tree, 1, 1, N, l, v)
        } else {
            val target = input.nextToken().toInt()
            val (l, r) = mapping[target]
            val ans = query(tree, 1, 1, N, l ,r)
            writer.write("${ans}\n")
        }
    }
    writer.flush()
}