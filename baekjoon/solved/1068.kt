package `1068`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

data class Node(var parent: Node?, val data: Int, val children: ArrayList<Node>)

fun count(node: Node): Int {
    if (node.children.isEmpty())
        return 1
    return node.children.sumOf { count(it) }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val nodeArr = Array<Node?>(N) { Node(null, it, arrayListOf()) }
    val stringTokenizer = StringTokenizer(reader.readLine())
    var root = -1
    for (i in 0 until N) {
        val p = stringTokenizer.nextToken().toInt()
        if (p == -1)
            root = i
        else {
            nodeArr[i]!!.parent = nodeArr[p]
            nodeArr[p]!!.children.add(nodeArr[i]!!)
        }
    }
    val target = reader.readLine().toInt()
    nodeArr[target]?.parent?.children?.remove(nodeArr[target])
    nodeArr[target]?.parent = null
    nodeArr[target] = null
    val leafCount = if (nodeArr[root] != null) count(nodeArr[root]!!) else 0
    writer.write("${leafCount}")
    writer.flush()
}