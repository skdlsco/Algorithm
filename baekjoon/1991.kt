import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class `1991`(val size: Int) {
    val tree = Array<Node>(size) { `1991`.Node(it) }

    class Node(val data: Int, var left: Node? = null, var right: Node? = null)

    fun getNode(name: Int?): Node? {
        return name?.let { tree[it] }
    }

    fun addNode(name: Int, left: Int?, right: Int?) {
        getNode(name)?.let {
            it.left = getNode(left)
            it.right = getNode(right)
        }
    }

    fun preorder(node: Node = tree[0]) {
        print((node.data + 'A'.code).toChar())
        node.left?.let { preorder(it) }
        node.right?.let { preorder(it) }
    }

    fun inorder(node: Node = tree[0]) {
        node.left?.let { inorder(it) }
        print((node.data + 'A'.code).toChar())
        node.right?.let { inorder(it) }
    }

    fun postorder(node: Node = tree[0]) {
        node.left?.let { postorder(it) }
        node.right?.let { postorder(it) }
        print((node.data + 'A'.code).toChar())
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val tree = `1991`(N)
    repeat(N) {
        val (n, l, r) = reader.readLine().split(" ").map {
            if (it == ".")
                null
            else
                it[0].code - 'A'.code
        }
        tree.addNode(n!!, l, r)
    }
    tree.preorder()
    println()
    tree.inorder()
    println()
    tree.postorder()
    println()
}