package `20040`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val root = Array<Int>(500001) { it }

fun find(node: Int): Int {
    if (root[node] == node)
        return node
    root[node] = find(root[node])
    return root[node]
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    var ans = 0
    for (i in 1..m) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        val aRoot = find(a)
        val bRoot = find(b)
        if (aRoot == bRoot) {
            ans = i
            break
        }
        root[bRoot] = aRoot
    }
    writer.write("${ans}\n")
    writer.flush()
}
    