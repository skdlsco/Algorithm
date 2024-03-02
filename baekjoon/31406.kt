package `E`

import java.util.StringTokenizer

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

class Node(
        val num: Int,
        var idx: Int = 0,
        var parent: Node? = null,
        val children: ArrayList<Node> = ArrayList<Node>(),
        var isOpened: Boolean = false
)

val arr = Array<Node>(2001) { Node(it) }
var cur = arr[0]

fun up() {
    var node = cur
    if (node.idx == 0 && node.parent!!.num == 1)
        return
    if (node.idx == 0) {
        cur = node.parent!!
    } else {
        node = node.parent!!.children[node.idx - 1]
        while (node.isOpened && node.children.isNotEmpty())
            node = node.children.last()
        cur = node
    }
}

fun down() {
    var node = cur
    if (node.isOpened && node.children.isNotEmpty()) {
        cur = node.children.first()
        return
    }
    while (node.parent != null && node.idx == node.parent!!.children.lastIndex)
        node = node.parent!!
    if (node.parent == null)
        return
    node = node.parent!!.children[node.idx + 1]
    cur = node
}

fun main() {
    val (N, Q) = reader.readLine().split(" ").map { it.toInt() }
    for (i in 1..N) {
        val st = StringTokenizer(reader.readLine())
        repeat(st.nextToken().toInt()) {
            val node = st.nextToken().toInt()
            if (i == 1 && it == 0)
                cur = arr[node]
            arr[node].idx = it
            arr[node].parent = arr[i]
            arr[i].children.add(arr[node])
        }
    }
    arr[1].isOpened = true
    repeat(Q) {
        val st = StringTokenizer(reader.readLine())
        val c = st.nextToken()
        if (c == "toggle") {
            cur.isOpened = !cur.isOpened
        } else {
            var v = st.nextToken().toInt()
            if (v < 0) {
                v = -v
                repeat(v) {
                    up()
                }
            } else {
                repeat(v) {
                    down()
                }
            }
            writer.write("${cur.num}\n")
        }
    }
    writer.flush()
}
    