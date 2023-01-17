package `13549`

import java.util.*

fun addNext(nextPos: Int, visited: Array<Int>, queue: Queue<Pair<Int, Int>>, depth: Int) {
    if (nextPos in 0..100000 && depth < visited[nextPos]) {
        visited[nextPos] = depth
        queue.add(Pair(depth + 1, nextPos))
    }
}

fun find(N: Int, K: Int): Int {
    val visited = Array<Int>(100001) { Int.MAX_VALUE }
    visited[N] = 0
    var fastest = Int.MAX_VALUE
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(0, N))
    while (queue.isNotEmpty()) {
        val (depth, pos) = queue.pop()
        if (pos == K) {
            if (depth < fastest)
                fastest = depth
        }
        if (depth >= fastest)
            continue
        addNext(pos - 1, visited, queue, depth)
        addNext(pos + 1, visited, queue, depth)
        if (pos * 2 - K + depth < fastest)
            addNext(pos * 2, visited, queue, depth - 1)
    }
    return fastest
}

fun main() {
    val scanner = Scanner(System.`in`)
    val (N, K) = scanner.nextLine().split(" ").map { it.toInt() }
    println(find(N, K))
}