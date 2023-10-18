package `11779`

import java.lang.Integer.max
import java.lang.Integer.min
import java.util.LinkedList
import java.util.PriorityQueue

data class Node(var pos: Int, var cost: Int) {

}

fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val graph = Array<LinkedList<Node>>(n) { LinkedList() }
    var cnt = 0
    val map = Array<Node>(n) { Node(-1, -1) }
    repeat(m) {
        val (s, e, c) = readLine()!!.split(" ").map { it.toInt() }
        graph[s - 1].add(Node(e - 1, c))
    }
    val (start, end) = readLine()!!.split(" ").map { it.toInt() - 1 }
    val queue = PriorityQueue<Node>(compareBy { it.cost })
    map[start] = Node(0, 0)
    queue.add(Node(start, 0))
    while (queue.isNotEmpty()) {
        val now = queue.remove()
        if (now.cost > map[now.pos].cost)
            continue
        graph[now.pos].forEach {
            val nextCost = it.cost + now.cost;
            if (map[it.pos].cost == -1 || map[it.pos].cost > nextCost) {
                map[it.pos].pos = now.pos
                map[it.pos].cost = nextCost
                queue.add(Node(it.pos, nextCost))
            }
        }
    }
    val route = ArrayList<Node>()
    var now = Node(end, 0)
    while (now.pos != start) {
        route.add(now)
        now = map[now.pos]
    }
    route.add(now)
    println(map[end].cost)
    println(route.size)
    println(route.reversed().joinToString(" ") { (it.pos + 1).toString() })
}