package `2206`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import kotlin.math.min

data class Node(val x: Int = 0, val y: Int = 0, val depth: Int = 1, val isCrashed: Boolean = false)

fun next(
    N: Int,
    M: Int,
    map: Array<IntArray>,
    visited: Array<Array<IntArray>>,
    x: Int,
    y: Int,
    depth: Int,
    isCrashed: Boolean
): Node? {
    if (x < 0 || y < 0 || x >= M || y >= N)
        return null
    val isWall = map[y][x] == 1
    if (isCrashed && isWall)
        return null
    if (isCrashed) {
        if (visited[0][y][x] == -1 || visited[0][y][x] > depth){
            visited[0][y][x] = depth
            return Node(x, y, depth, true)
        }
    } else if (isWall) {
        if (visited[0][y][x] == -1 || visited[0][y][x] > depth) {
            visited[0][y][x] = depth
            return Node(x, y, depth, true)
        }
    } else {
        if (visited[1][y][x] == -1 || visited[1][y][x] > depth) {
            visited[1][y][x] = depth
            return Node(x, y, depth, false)
        }
    }
    return null
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine()!!.split(" ").map { it.toInt() }
    val map = Array<IntArray>(N) { reader.readLine()!!.map { it.digitToInt() }.toIntArray() }
    val visited = Array<Array<IntArray>>(2) { Array(N) { IntArray(M) { -1 } } }

    val queue = LinkedList<Node>()
    visited[0][0][0] = 1
    visited[1][0][0] = 1
    queue.add(Node())
    while (queue.isNotEmpty()) {
        val node = queue.remove()

        next(N, M, map, visited, node.x + 1, node.y, node.depth + 1, node.isCrashed)?.let { queue.add(it) }
        next(N, M, map, visited, node.x - 1, node.y, node.depth + 1, node.isCrashed)?.let { queue.add(it) }
        next(N, M, map, visited, node.x, node.y + 1, node.depth + 1, node.isCrashed)?.let { queue.add(it) }
        next(N, M, map, visited, node.x, node.y - 1, node.depth + 1, node.isCrashed)?.let { queue.add(it) }
    }
    val crashed = visited[0][N - 1][M - 1]
    val normal = visited[1][N - 1][M - 1]
    if (crashed == -1)
        println(normal)
    else if (normal == -1)
        println(crashed)
    else
        println(min(crashed, normal))
}