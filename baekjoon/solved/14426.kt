package `14426`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Node(val child: MutableMap<Char, Node> = HashMap())

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val root = Node()
    repeat(N) {
        val s = reader.readLine()
        var cur = root
        for (c in s) {
            if (!cur.child.containsKey(c)) {
                cur.child[c] = Node()
            }
            cur = cur.child[c]!!
        }
    }
    var ans = 0
    repeat(M) {
        val s = reader.readLine()
        var cur = root
        val cond = s.all {
            if (!cur.child.containsKey(it))
                false
            else {
                cur = cur.child[it]!!
                true
            }
        }
        if (cond)
            ans++
    }
    writer.write("${ans}\n")
    writer.flush()
}
    