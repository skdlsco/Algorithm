object `2263` {
    class Node(val data: Int, var left: Node? = null, var right: Node? = null) {}

    fun createTree(inOrder: List<Int>, postOrder: List<Int>, iStart: Int, iEnd: Int, pStart: Int, pEnd: Int): Node {
        val center = postOrder[pEnd]
        val centerPos = inOrder.indexOf(center)
        val leftSize = centerPos - iStart
        val root = Node(postOrder[pEnd])
        if (leftSize != 0)
            root.left = createTree(inOrder, postOrder, iStart, centerPos - 1, pStart, pStart + leftSize - 1)
        if (iEnd != centerPos)
            root.right = createTree(inOrder, postOrder, centerPos + 1, iEnd, pStart + leftSize, pEnd - 1)
        return root
    }

    fun preOrder(node: Node) {
        print("${node.data} ")
        node.left?.let { preOrder(it) }
        node.right?.let { preOrder(it) }
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val inOrder = readLine()!!.split(" ").map { it.toInt() }
    val postOrder = readLine()!!.split(" ").map { it.toInt() }

    val root = `2263`.createTree(inOrder, postOrder, 0, n - 1, 0, n - 1)
    `2263`.preOrder(root)
}