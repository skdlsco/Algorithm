package `1327`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

data class Node(
    val value: Int,
    var cnt: Int = -1,
    val parent: Node?,
    val children: ArrayList<Node> = ArrayList()
) {
    override fun toString(): String {
        return "$value $cnt"
    }
}

fun isVisited(visited: Array<Int>, depth: Int, value: Int): Boolean {
    for (i in 0 until depth) {
        if (visited[i] == value)
            return true
    }
    return false
}

fun generateTree(N: Int, visited: Array<Int>, parent: Node?, depth: Int, value: Int): Node {
    val node = Node(value, -1, parent)
    if (depth < N) {
        for (num in 1..N) {
            if (!isVisited(visited, depth, num)) {
                visited[depth] = num
                node.children.add(generateTree(N, visited, node, depth + 1, num))
            }
        }
    }
    return node
}

// true -> 더 작은 값이 들어왔음
// false -> 같거나 큰 값이 들어왔음
fun update(root: Node, arr: Array<Int>, cnt: Int): Boolean {
    var node = root
    for (num in arr) {
        for (next in node.children) {
            if (next.value == num) {
                node = next
                break
            }
        }
    }
    if (node.cnt == -1 || node.cnt > cnt) {
        node.cnt = cnt
        return true
    }
    return false
}

fun find(root: Node, arr: Array<Int>): Node {
    var node = root
    for (num in arr) {
        for (next in node.children) {
            if (next.value == num) {
                node = next
                break
            }
        }
    }
    return node
}

fun findAscending(root: Node): Int {
    var node = root
    while (node.children.isNotEmpty()) {
        node = node.children.first()
    }
    return node.cnt
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val visited = Array<Int>(N + 1) { 0 }
    val tree = generateTree(N, visited, null, 0, 0)
    val arr = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    update(tree, arr, 0)
    val queue = LinkedList<Array<Int>>()
    queue.add(arr)
    while (queue.isNotEmpty()) {
        val now = queue.remove()
        val node = find(tree, now)
        for (s in 0..N - K) {
            val next = Array<Int>(N) { 0 }
            var idx = 0
            for (i in 0 until s) {
                next[idx] = now[i]
                idx++
            }
            for (i in s + K - 1 downTo s) {
                next[idx] = now[i]
                idx++
            }
            for (i in s + K until N) {
                next[idx] = now[i]
                idx++
            }
            if (update(tree, next, node.cnt + 1))
                queue.add(next)
        }
    }
    update(tree, arr, 0)
    println(findAscending(tree))
}