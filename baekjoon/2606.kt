package `2606`

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val M = scanner.nextInt()
    val graph = Array<ArrayList<Int>>(N) { ArrayList() }
    repeat(M) {
        val p1 = scanner.nextInt() - 1
        val p2 = scanner.nextInt() - 1
        graph[p1].add(p2)
        graph[p2].add(p1)
    }
    val visited = Array<Boolean>(N) { false }
    visited[0] = true
    val queue = LinkedList<Int>()
    queue.push(0)
    while (queue.isNotEmpty()) {
        val start = queue.pop()
        graph[start].forEach {
            if (!visited[it]) {
                visited[it] = true
                queue.push(it)
            }
        }
    }
    println(visited.count { it } - 1)
}