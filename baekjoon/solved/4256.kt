package `4256`

import java.io.*
import java.util.StringTokenizer

data class Node(val value: Int, var right: Node? = null, var left: Node? = null)

class Solution(
    private val reader: BufferedReader,
    private val writer: BufferedWriter
) {
    val N: Int
    val preorder: Array<Int>
    val inorder: Array<Int>

    init {
        N = reader.readLine().toInt()
        var tokenizer = StringTokenizer(reader.readLine())
        preorder = Array(N) {
            tokenizer.nextToken().toInt()
        }
        tokenizer = StringTokenizer(reader.readLine())
        inorder =  Array(N) {
            tokenizer.nextToken().toInt()
        }
    }

    fun constructTree(preS: Int, preEnd: Int, inS: Int, inE: Int): Node {
        val node = Node(preorder[preS])
        val inorderIdx = inorder.indexOf(node.value)
        val leftNodeCount = inorderIdx - inS
        val rightNodeCount = inE - inorderIdx
        if (leftNodeCount > 0)
            node.left = constructTree(preS + 1, preS + leftNodeCount, inS, inorderIdx - 1)
        if (rightNodeCount > 0)
            node.right = constructTree(preEnd - rightNodeCount + 1, preEnd, inorderIdx + 1, inE)
        return node
    }

    fun postorder(node: Node) {
        node.left?.let { postorder(it) }
        node.right?.let { postorder(it) }
        writer.write("${node.value} ")
    }

    fun solve() {
        val root = constructTree(0, N - 1, 0, N - 1)
        postorder(root)
        writer.write("\n")
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        Solution(reader, writer).solve()
    }
    writer.flush()
}