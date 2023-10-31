import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayList
import java.util.LinkedList
import java.util.PriorityQueue

data class Pos(val x: Int, val y: Int)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun getEdge(N: Int, M: Int, map: Array<Array<Int>>, start: Pos): Array<Int> {
    val visited = Array<Array<Boolean>>(N) { Array(N) { false } }
    val ans = Array<Int>(M + 1) { -1 }
    val queue = LinkedList<Pair<Int, Pos>>()
    queue.add(Pair(0, start))
    visited[start.y][start.x] = true
    while (queue.isNotEmpty()) {
        val (dist, pos) = queue.pop()
        if (map[pos.y][pos.x] > 0) {
            ans[map[pos.y][pos.x]] = dist
        }
        for (d in 0 until 4) {
            val nx = pos.x + dx[d]
            val ny = pos.y + dy[d]
            if (!visited[ny][nx] && map[ny][nx] != -1) {
                visited[ny][nx] = true
                queue.add(Pair(dist + 1, Pos(nx, ny)))
            }
        }
    }
    return ans
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var (N, M) = reader.readLine().split(" ").map { it.toInt() }
    M++
    val map = Array<Array<Int>>(N) { Array(N) { -1 } }
    val node = Array<Pos>(M + 1) { Pos(0, 0) }
    var nodeNum = 1
    for (y in 0 until N) {
        val row = reader.readLine()
        for (x in 0 until N) {
            if (row[x] == '1')
                map[y][x] = -1
            else if (row[x] == '0')
                map[y][x] = 0
            else {
                map[y][x] = nodeNum
                node[nodeNum++] = Pos(x, y)
            }
        }
    }
    val graph = Array<Array<Int>>(M + 1) { Array(M + 1) { -1 } }
    for (i in 1..M) {
        graph[i] = getEdge(N, M, map, node[i])
    }
    // MST
    var ans = 0
    val visited = Array(M + 1) { false }
    val pq = PriorityQueue<Pair<Int, Int>>() { o1, o2 ->
        o1.second - o2.second
    }
    pq.add(Pair(1, 0))
    while (pq.isNotEmpty()) {
        val (v, dist) = pq.remove()
        if (visited[v])
            continue
        ans += dist
        visited[v] = true
        for (next in 1..M) {
            if (!visited[next] && graph[v][next] != -1) {
                pq.add(Pair(next, graph[v][next]))
            }
        }
    }
    var isFoundAllKey = true
    for (i in 1..M) {
        if (!visited[i])
            isFoundAllKey = false
    }
    if (isFoundAllKey)
        writer.write("${ans}\n")
    else
        writer.write("-1\n")
    writer.flush()
}
