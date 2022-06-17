import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

fun bfs(visited: MutableList<MutableList<Boolean>>, size: Int, start: Pair<Int, Int>): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    var cnt = 0
    queue.push(start)
    visited[start.second][start.first] = true
    while (queue.isNotEmpty()) {
        val now = queue.pop();
        cnt++
        if (now.first > 0 && !visited[now.second][now.first - 1]) {
            visited[now.second][now.first - 1] = true
            queue.push(Pair(now.first - 1, now.second))
        }
        if (now.first < size - 1 && !visited[now.second][now.first + 1]) {
            visited[now.second][now.first + 1] = true
            queue.push(Pair(now.first + 1, now.second))
        }
        if (now.second > 0 && !visited[now.second - 1][now.first]) {
            visited[now.second - 1][now.first] = true
            queue.push(Pair(now.first, now.second - 1))
        }
        if (now.second < size - 1 && !visited[now.second + 1][now.first]) {
            visited[now.second + 1][now.first] = true
            queue.push(Pair(now.first, now.second + 1))
        }
    }
    return cnt
}

fun getNextStartPoint(visited: MutableList<MutableList<Boolean>>): Pair<Int, Int> {
    visited.forEachIndexed { y, list ->
        list.forEachIndexed { x, b ->
            if (!b)
                return Pair(x, y)
        }
    }
    return Pair(-1, -1)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val visited = MutableList<MutableList<Boolean>>(N) { reader.readLine().map { it == '0' }.toMutableList() }
    val result = ArrayList<Int>()
    var startPoint = getNextStartPoint(visited)

    while (startPoint.first != -1) {
        result.add(bfs(visited, N, startPoint))
        startPoint = getNextStartPoint(visited)
    }

    result.sort()
    println(result.size)
    result.forEach {
        println(it)
    }
}