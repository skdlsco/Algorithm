package `14725`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ceil
import kotlin.math.max

data class Node(val key: String, val map: TreeMap<String, Node> = TreeMap())

fun printTree(sb: StringBuilder, node: Node, depth: Int) {
    if (depth > -1) {
        repeat(depth) {
            sb.append("--")
        }
        sb.append("${node.key}\n")
    }
    val children = node.map.toList().sortedBy { it.first }
    for (next in children) {
        printTree(sb, next.second, depth + 1)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val root = Node("root")
    repeat(N) {
        val tokenizer = StringTokenizer(reader.readLine())
        val M = tokenizer.nextToken().toInt()
        var cur = root
        repeat(M) {
            val key = tokenizer.nextToken()
            if (!cur.map.contains(key))
                cur.map[key] = Node(key)
            cur = cur.map[key]!!
        }
    }
    val sb = StringBuilder()
    printTree(sb, root, -1)
    writer.write(sb.toString())
    writer.flush()
}
