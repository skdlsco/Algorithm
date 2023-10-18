package `5639`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class `5639` {

    class Node(val data: Int, var left: Node? = null, var right: Node? = null)

    companion object {
        fun print(node: Node) {
            node.left?.let { print(it) }
            node.right?.let { print(it) }
            println(node.data)
        }
    }
}

fun main() {
    var num: Int = 0
    var head: `5639`.Node? = null
    while (readLine()?.let { num = it.toInt() } != null) {
        val node = `5639`.Node(num)
        if (head == null)
            head = node
        else {
            var now = head!!
            while (true) {
                if (now.data > node.data) {
                    if (now.left == null) {
                        now.left = node
                        break
                    } else
                        now = now.left!!
                } else {
                    if (now.right == null) {
                        now.right = node
                        break
                    } else
                        now = now.right!!
                }
            }
        }
    }
    `5639`.print(head!!)
}