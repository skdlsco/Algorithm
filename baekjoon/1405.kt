package `1405`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, -1, 1)

fun move(
    N: Int,
    visited: Array<Array<Boolean>>,
    probability: Array<Int>,
    depth: Int,
    now: Double,
    x: Int,
    y: Int
): Double {
    if (depth == N) {
        return now
    }
    return (0..3).sumOf {
        val nextX = x + dx[it]
        val nextY = y + dy[it]
        if (!visited[nextY][nextX] && probability[it] > 0) {
            visited[nextY][nextX] = true
            val result = move(N, visited, probability, depth + 1, now * probability[it] / 100, nextX, nextY)
            visited[nextY][nextX] = false
            result
        } else
            0.0
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val stringTokenizer = StringTokenizer(reader.readLine())
    val N = stringTokenizer.nextToken().toInt()
    val visited = Array<Array<Boolean>>(30) { Array(30) { false } }
    val probability = Array<Int>(4) { stringTokenizer.nextToken().toInt() }

    visited[15][15] = true
    val result =move(N, visited, probability, 0, 1.0, 15, 15)
    println(String.format("%.11f", result))
}