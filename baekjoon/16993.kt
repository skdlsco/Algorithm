import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.*

const val DEFAULT = -1_000_000_001L

data class Node(val left: Long = DEFAULT, val right: Long = DEFAULT, val sum: Long = DEFAULT, val max: Long = DEFAULT)

fun init(tree: Array<Node>, arr: List<Long>, node: Int, s: Int, e: Int) {
    if (s == e) {
        tree[node] = Node(arr[s], arr[s], arr[s], arr[s])
    } else {
        init(tree, arr, node * 2, s, (s + e) / 2)
        init(tree, arr, node * 2 + 1, (s + e) / 2 + 1, e)
        val lNode = tree[node * 2]
        val rNode = tree[node * 2 + 1]
        val leftSum = maxOf(lNode.left, lNode.sum + rNode.left)
        val rightSum = maxOf(rNode.right, rNode.sum + lNode.right)
        val sum = lNode.sum + rNode.sum
        tree[node] = Node(
                leftSum,
                rightSum,
                sum,
                maxOf(leftSum, rightSum, sum, lNode.right + rNode.left, lNode.max, rNode.max)
        )
    }
}

fun query(tree: Array<Node>, node: Int, s: Int, e: Int, left: Int, right: Int): Node {
    if (right < s || e < left)
        return Node()
    if (left <= s && e <= right)
        return tree[node]
    val lNode = query(tree, node * 2, s, (s + e) / 2, left, right)
    val rNode = query(tree, node * 2 + 1, (s + e) / 2 + 1, e, left, right)
    val leftSum = maxOf(lNode.left, lNode.sum + rNode.left)
    val rightSum = maxOf(rNode.right, rNode.sum + lNode.right)
    val sum = lNode.sum + rNode.sum
    if (lNode.left == DEFAULT)
        return rNode
    if (rNode.left == DEFAULT)
        return lNode
    return Node(
            leftSum,
            rightSum,
            sum,
            maxOf(leftSum, rightSum, sum, lNode.right + rNode.left, lNode.max, rNode.max)
    )
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val treeSize = 1 shl (ceil(log2(N + 1.0)).toInt() + 1)
    val tree = Array<Node>(treeSize) { Node() }
    val arr = reader.readLine().split(" ").map { it.toLong() }
    val M = reader.readLine().toInt()
    init(tree, arr, 1, 0, N - 1)
    repeat(M) {
        val (l, r) = reader.readLine().split(" ").map { it.toInt() - 1 }
        val result = query(tree, 1, 0, N - 1, l, r)
        writer.write("${result.max}\n")
    }
    writer.flush()
}