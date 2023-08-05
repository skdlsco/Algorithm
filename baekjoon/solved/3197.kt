package `3197`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.PriorityQueue

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

data class Pos(val x: Int, val y: Int) {
    fun toNode(): Int = x + y * 1500
}

class DisjointSet(val size: Int) {
    val arr = Array<Int>(size + 1) { it }

    fun findRoot(node: Int): Int {
        if (arr[node] == node)
            return node
        val root = findRoot(arr[node])
        arr[node] = root
        return root
    }

    fun union(a: Int, b: Int) {
        val aRoot = findRoot(a)
        val bRoot = findRoot(b)
        arr[bRoot] = aRoot
    }
}

class Solution(val R: Int, val C: Int, val map: Array<Array<Char>>) {
    val disjointSet = DisjointSet(1500 * 1500)
    var swanA = Pos(-1, -1)
    var swanB = Pos(-1, -1)
    val visited = Array<Array<Boolean>>(R) { Array(C) { false } }
    val queue = LinkedList<Pos>()
    val next = LinkedList<Pos>()

    fun solve(): Int {
        for (y in 0 until R) {
            for (x in 0 until C) {
                if (map[y][x] == 'L') {
                    map[y][x] = '.'
                    if (swanA.x == -1)
                        swanA = Pos(x, y)
                    else
                        swanB = Pos(x, y)
                }
                if (map[y][x] != 'X') {
                    for (i in 0 until 4) {
                        val nx = x + dx[i]
                        val ny = y + dy[i]
                        if (nx in 0 until C && ny in 0 until R) {
                            val np = Pos(nx, ny)
                            if (map[ny][nx] != 'X') {
                                disjointSet.union(Pos(x, y).toNode(), np.toNode())
                            } else if (!visited[ny][nx]) {
                                next.add(Pos(nx, ny))
                                visited[ny][nx] = true
                            }
                        }
                    }
                }
            }
        }

        var day = 0
        while (true) {
            val aRoot = disjointSet.findRoot(swanA.toNode())
            val bRoot = disjointSet.findRoot(swanB.toNode())
            if (aRoot == bRoot)
                return day

            // 얼음 녹이기
            queue.addAll(next)
            next.clear()
            while (queue.isNotEmpty()) {
                val pos = queue.remove()
                val (x, y) = pos
                map[y][x] = '.'
                for (i in 0 until 4) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]
                    if (nx in 0 until C && ny in 0 until R) {
                        val np = Pos(nx, ny)
                        if (!visited[ny][nx])
                            next.add(np)
                        if (map[ny][nx] != 'X')
                            disjointSet.union(pos.toNode(), np.toNode())
                        visited[ny][nx] = true
                    }
                }
            }
            day++
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (R, C) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Char>>(R) { Array(C) { '.' } }

    for (y in 0 until R) {
        val line = reader.readLine()
        for (x in 0 until C) {
            map[y][x] = line[x]
        }
    }
    val solution = Solution(R, C, map)
    val ans = solution.solve()
    writer.write("${ans}\n")
    writer.flush()
}