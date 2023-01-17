package `19581`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max
import java.util.*
import kotlin.collections.ArrayList

class Node(val node: Int, val weight: Int)

fun bfs(
    graph: Array<ArrayList<Node>>,
    start: Int,
    except: Int = -1
): Node {
    val queue = LinkedList<Node>()
    var result = Node(start, 0)
    val visited = Array<Boolean>(graph.size) { false }
    queue.add(Node(start, 0))
    while (queue.isNotEmpty()) {
        val node = queue.remove()
        visited[node.node] = true
        if (node.weight > result.weight && node.node != except)
            result = node
        graph[node.node].filter { !visited[it.node] }.forEach {
            visited[it.node] = true
            queue.add(Node(it.node, node.weight + it.weight))
        }
    }
    return result
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val graph = Array<ArrayList<Node>>(N) { ArrayList() }
    repeat(N - 1) {
        val (start, end, cost) = reader.readLine()!!.split(" ").map { it.toInt() - 1 }
        graph[start].add(Node(end, cost + 1))
        graph[end].add(Node(start, cost + 1))
    }
    val a = bfs(graph, 0).node
    val b = bfs(graph, a).node
    val result = max(bfs(graph, b, a).weight, bfs(graph, a, b).weight)
    println(result)
}