package `Main`

import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

data class Node(val n: Int, val idx: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        if (other.n != n)
            return n.compareTo(other.n)
        return idx.compareTo(other.idx)
    }
}

fun main() {
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").mapIndexed { index, s -> Node(s.toInt(), index) }.sorted()
    val ans = Array<Int>(N) { 0}
    for (i in 0 until N) {
        ans[arr[i].idx] = i;
    }
    writer.write(ans.joinToString(" "))
    writer.flush()
}