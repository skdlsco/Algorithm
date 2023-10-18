package `12851`

import java.util.*

fun addNext(nextPos: Int, visited: Array<Int>, queue: Queue<Pair<Int, Int>>, depth: Int) {
    if (nextPos in 0..100000 && depth <= visited[nextPos]) {
        visited[nextPos] = depth
        queue.add(Pair(depth + 1, nextPos))
    }
}

fun find(N: Int, K: Int): Pair<Int, Int> {
    val visited = Array<Int>(100001) { Int.MAX_VALUE }
    visited[N] = 0
    var fastest = Int.MAX_VALUE
    var cnt = 0
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(0, N))
    while (queue.isNotEmpty()) {
        val (depth, pos) = queue.pop()
        if (pos == K) {
            if (depth < fastest) {
                fastest = depth
                cnt = 1
            } else if (depth == fastest)
                cnt++
        }
        if (depth >= fastest)
            continue
        addNext(pos - 1, visited, queue, depth)
        addNext(pos + 1, visited, queue, depth)
        addNext(pos * 2, visited, queue, depth)
    }
    return Pair(fastest, cnt)
}

fun main() {
    val scanner = Scanner(System.`in`)
    val (N, K) = scanner.nextLine().split(" ").map { it.toInt() }
    val (time, cnt) = find(N, K)
    println("$time $cnt")
}