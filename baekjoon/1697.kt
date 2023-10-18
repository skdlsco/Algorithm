package `1697`

import java.util.*

fun addNext(nextPos: Int, visited: Array<Boolean>, nextQueue: Queue<Int>) {
    if (nextPos in 0..100000 && !visited[nextPos]) {
        visited[nextPos] = true
        nextQueue.add(nextPos)
    }
}

fun find(N: Int, K: Int): Int {
    val visited = Array<Boolean>(100001) { false }
    visited[N] = true
    val nextQueue = LinkedList<Int>()
    nextQueue.add(N)
    val nowQueue = LinkedList<Int>()
    var cnt = 0
    while (nextQueue.isNotEmpty()) {
        nowQueue.clear()
        nowQueue.addAll(nextQueue)
        nextQueue.clear()
        while (nowQueue.isNotEmpty()) {
            val pos = nowQueue.pop()
            if (pos == K)
                return cnt
            addNext(pos - 1, visited, nextQueue)
            addNext(pos + 1, visited, nextQueue)
            addNext(pos * 2, visited, nextQueue)
        }
        cnt++
    }
    return cnt
}

fun main() {
    val scanner = Scanner(System.`in`)
    val (N, K) = scanner.nextLine().split(" ").map { it.toInt() }
    println(find(N, K))
}