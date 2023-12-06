import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.*

data class Node(val min: Long = 1_000_000_001L, val max: Long = -1_000_000_001L, val diff: Long = 0)

fun calcNode(n1: Node, n2: Node) = Node(
        minOf(n1.min, n2.min),
        maxOf(n1.max, n2.max),
        maxOf(n1.diff, n2.diff, if (n2.max > n1.min) abs(n2.max - n1.min) else 0)
)

fun update(tree: Array<Node>, node: Int, s: Int, e: Int, idx: Int, v: Long) {
    if (idx < s || e < idx)
        return
    if (s == e) {
        tree[node] = Node(v, v, 0)
    } else {
        update(tree, node * 2, s, (s + e) / 2, idx, v)
        update(tree, node * 2 + 1, (s + e) / 2 + 1, e, idx, v)
        tree[node] = calcNode(tree[node * 2], tree[node * 2 + 1])
    }
}

fun query(tree: Array<Node>, node: Int, s: Int, e: Int, left: Int, right: Int): Node {
    if (e < left || right < s)
        return Node()
    if (left <= s && e <= right) {
        return tree[node]
    }
    val lResult = query(tree, node * 2, s, (s + e) / 2, left, right)
    val rResult = query(tree, node * 2 + 1, (s + e) / 2 + 1, e, left, right)
    return calcNode(lResult, rResult)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val treeSize = 1 shl (ceil(log2(N + 1.0)).toInt() + 1)
    val tree = Array<Node>(treeSize) { Node() }
    reader.readLine().split(" ").map { it.toLong() }.withIndex().forEach {
        update(tree, 1, 1, N, it.index + 1, it.value)

    }
    val Q = reader.readLine().toInt()
    repeat(Q) {
        val input = StringTokenizer(reader.readLine())
        val command = input.nextToken().toInt()
        if (command == 1) {
            val idx = input.nextToken().toInt()
            val v = input.nextToken().toLong()
            update(tree, 1, 1, N, idx, v)
        } else {
            val l = input.nextToken().toInt()
            val r = input.nextToken().toInt()
            writer.write("${query(tree, 1, 1, N, l, r).diff}\n")
        }
    }
    writer.flush()
}