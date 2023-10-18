package `2178`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val (N, M) = scanner.nextLine().split(" ").map { it.toInt() }
    val isVisited = Array<Array<Boolean>>(N) { scanner.nextLine().map { it == '0' }.toTypedArray() }
    val queue = LinkedList<Pair<Int, Int>>()
    val nextQueue = LinkedList<Pair<Int, Int>>()
    var cnt = 1
    isVisited[0][0] = true
    nextQueue.push(Pair(0, 0))
    while (nextQueue.isNotEmpty()) {
        queue.clear()
        queue.addAll(nextQueue)
        if (queue.find { it.first == (M - 1) && it.second == (N - 1) } != null)
            break
        nextQueue.clear()
        while (queue.isNotEmpty()) {
            val (x, y) = queue.pop()
            if (x > 0 && !isVisited[y][x - 1]) {
                isVisited[y][x - 1] = true
                nextQueue.push(Pair(x - 1, y))
            }
            if (x < M - 1 && !isVisited[y][x + 1]) {
                isVisited[y][x + 1] = true
                nextQueue.push(Pair(x + 1, y))
            }
            if (y > 0 && !isVisited[y - 1][x]) {
                isVisited[y - 1][x] = true
                nextQueue.push(Pair(x, y - 1))
            }
            if (y < N - 1 && !isVisited[y + 1][x]) {
                isVisited[y + 1][x] = true
                nextQueue.push(Pair(x, y + 1))
            }
        }
        cnt++
    }
    println(cnt)
}