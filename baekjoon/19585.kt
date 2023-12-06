import java.io.*
import kotlin.collections.HashSet

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

class Node(
    var exist: Boolean = false,
    val children: Array<Node?> = Array(26) { null }
) {
    fun getChild(c: Char): Node? {
        return children[c.code - 'a'.code]
    }
}

fun main() {
    val (C, N) = reader.readLine().split(" ").map { it.toInt() }
    val colorRoot = Node()
    repeat(C) {
        val s = reader.readLine()
        var cur = colorRoot
        for (c in s) {
            val next = cur.getChild(c) ?: run {
                val new = Node()
                cur.children[c.code - 'a'.code] = new
                new
            }
            cur = next
        }
        cur.exist = true
    }

    val hash = HashSet<String>()
    repeat(N) {
        hash.add(reader.readLine())
    }
    repeat(reader.readLine().toInt()) {
        val S = reader.readLine()
        var flag = false
        var color = colorRoot
        for ((idx, c) in S.withIndex()) {
            if (color.exist && S.substring(idx) in hash) {
                flag = true
                break
            }
            val child = color.getChild(c) ?: break
            color = child
        }
        if (flag)
            writer.write("Yes\n")
        else
            writer.write("No\n")
    }
    writer.flush()
}