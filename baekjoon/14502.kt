import java.util.*

object `14502` {


    fun bfs(N: Int, M: Int, map: Array<Array<Int>>): Int {
        val queue = LinkedList<Pair<Int, Int>>()
        val visited = Array<Array<Boolean>>(N) { Array(M) { false } }
        map.forEachIndexed { y, ints ->
            ints.forEachIndexed { x, i ->
                if (i == 2)
                    queue.push(Pair(x, y))
                if (i != 0)
                    visited[y][x] = true
            }
        }
        while (queue.isNotEmpty()) {
            val (x, y) = queue.remove()
            if (x > 0 && !visited[y][x - 1]) {
                queue.push(Pair(x - 1, y))
                visited[y][x - 1] = true
            }
            if (x + 1 < M && !visited[y][x + 1]) {
                queue.push(Pair(x + 1, y))
                visited[y][x + 1] = true
            }
            if (y > 0 && !visited[y - 1][x]) {
                queue.push(Pair(x, y - 1))
                visited[y - 1][x] = true
            }
            if (y + 1 < N && !visited[y + 1][x]) {
                queue.push(Pair(x, y + 1))
                visited[y + 1][x] = true
            }
        }
        return visited.sumOf {
            it.count { !it }
        }
    }

    fun getMax(N: Int, M: Int, map: Array<Array<Int>>, wallCnt: Int): Int {
        if (wallCnt == 3)
            return bfs(N, M, map)
        return (0 until N).maxOf { y ->
            (0 until M).maxOf { x ->
                if (map[y][x] == 0) {
                    map[y][x] = 1
                    val max = getMax(N, M, map, wallCnt + 1)
                    map[y][x] = 0
                    max
                } else 0
            }
        }
    }
}

fun main() {
    val (N, M) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array<Array<Int>>(N) { readLine()!!.split(" ").map { it.toInt() }.toTypedArray() }
    println(`14502`.getMax(N, M, map, 0))
}