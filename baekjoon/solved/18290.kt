package `18290`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max

class Game(val N: Int, val M: Int, val K: Int, val map: Array<Array<Int>>) {
    val visited = Array<Array<Int>>(N) { Array(M) { 0 } }

    private fun setVisited(y: Int, x: Int) {
        visited[y][x] = 1
        if (y > 0)
            visited[y - 1][x]--
        if (y + 1 < N)
            visited[y + 1][x]--
        if (x > 0)
            visited[y][x - 1]--
        if (x + 1 < M)
            visited[y][x + 1]--
    }

    private fun resetVisited(y: Int, x: Int) {
        visited[y][x] = 0
        if (y > 0)
            visited[y - 1][x]++
        if (y + 1 < N)
            visited[y + 1][x]++
        if (x > 0)
            visited[y][x - 1]++
        if (x + 1 < M)
            visited[y][x + 1]++
    }

    private fun getMaxValue(r: Int, c: Int, depth: Int): Int {
        if (depth == K) {
            var sum = 0
            visited.forEachIndexed { y, ints ->
                ints.forEachIndexed { x, i ->
                    if (i == 1)
                        sum += map[y][x]
                }
            }
            return sum
        }
        var maxValue = Int.MIN_VALUE
        for (i in r * M + c until N * M) {
            val y = i / M
            val x = i % M
            if (visited[y][x] == 0) {
                setVisited(y, x)
                maxValue = max(getMaxValue(y, x, depth + 1), maxValue)
                resetVisited(y, x)
            }
        }
        return maxValue
    }

    fun getAnswer(): Int {
        return getMaxValue(0, 0, 0)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Int>>(N) { reader.readLine().split(" ").map { it.toInt() }.toTypedArray() }

    val result = Game(N, M, K, map).getAnswer()
    println(result)
}