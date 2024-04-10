import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

data class Node(val d: Int, val h: Int, val line: Int, val deka: Boolean) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        if (other.d != d)
            return other.d.compareTo(d)
        if (other.h != h)
            return other.h.compareTo(h)
        return line.compareTo(other.line)
    }
}

fun main() {
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val queue = Array<Queue<Node>>(M) { LinkedList<Node>() }
    for (i in 0 until N) {
        val (d, h) = reader.readLine().split(" ").map { it.toInt() }
        queue[i % M].add(Node(d, h, i % M, i == K))
    }
    val pq = PriorityQueue<Node>()
    queue.forEach {
        if (it.isNotEmpty()) pq.add(it.remove())
    }
    var ans = 0
    while (true) {
        val cur = pq.remove()
        if (queue[cur.line].isNotEmpty())
            pq.add(queue[cur.line].remove())
        if (cur.deka) {
            writer.write("${ans}\n")
            break
        }
        ans++
    }
    writer.flush()
}