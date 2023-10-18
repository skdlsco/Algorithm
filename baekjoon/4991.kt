package `4991`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import kotlin.math.max
import kotlin.math.min

data class Pos(val x: Int, val y: Int)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

class Solution(val w: Int, val h: Int, val map: Array<Array<Char>>) {
    val nodeArr = ArrayList<Pos>()
    fun getDistanceArr(start: Pos): Array<Int> {
        val ans = Array<Int>(nodeArr.size) { 0 }
        val queue = LinkedList<Pair<Int, Pos>>()
        val visited = Array<Array<Boolean>>(h) { Array(w) { false } }
        queue.add(Pair(0, start))
        visited[start.y][start.x] = true
        while (queue.isNotEmpty()) {
            val (d, pos) = queue.pop()
            val (x, y) = pos
            if (map[y][x] == '*') {
                val nodeIdx = nodeArr.indexOfFirst { it.x == x && it.y == y }
                if (nodeIdx != -1) {
                    ans[nodeIdx] = d
                }
            }
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx in 0 until w && ny in 0 until h &&
                    map[ny][nx] != 'x' && !visited[ny][nx]
                ) {
                    queue.add(Pair(d + 1, Pos(nx, ny)))
                    visited[ny][nx] = true
                }
            }
        }
        return ans
    }

    fun dfs(
        graph: Array<Array<Int>>,
        startDistArr: Array<Int>,
        cur: Int,
        cnt: Int,
        visited: Array<Boolean>
    ): Int {
        var ans = Int.MAX_VALUE
        if (cnt == nodeArr.size)
            return 0
        for (i in nodeArr.indices) {
            if (!visited[i]) {
                visited[i] = true
                val dist = if (cur == -1) startDistArr[i] else graph[cur][i]
                ans = min(ans, dfs(graph, startDistArr, i, cnt + 1, visited) + dist)
                visited[i] = false
            }
        }
        return ans
    }

    fun solve(): Int {
        // 노드 idx 매기기
        // 각 노드간 거리측정
        // 완전탐색으로 최단거리구하기
        var start = Pos(0, 0)
        for (y in 0 until h) {
            for (x in 0 until w) {
                if (map[y][x] == '*')
                    nodeArr.add(Pos(x, y))
                if (map[y][x] == 'o')
                    start = Pos(x, y)
            }
        }
        val startDistArr = getDistanceArr(start)
        if (startDistArr.contains(0))
            return -1
        val graph = Array<Array<Int>>(nodeArr.size) { getDistanceArr(nodeArr[it]) }
        // 최단거리 구하기
        return dfs(graph, startDistArr, -1, 0, Array(nodeArr.size) { false })
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    while (true) {
        val (w, h) = reader.readLine().split(" ").map { it.toInt() }
        if (w == 0 && h == 0)
            break

        val map = Array<Array<Char>>(h) { Array(w) { '.' } }
        for (y in 0 until h) {
            val line = reader.readLine()
            for (x in 0 until w) {
                map[y][x] = line[x]
            }
        }
        val solution = Solution(w, h, map)
        writer.write("${solution.solve()}\n")
    }
    writer.flush()
}