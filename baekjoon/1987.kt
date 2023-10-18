package `1987`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun getAlphaIndex(c: Char): Int {
    return c.code - 'A'.code
}

fun dfs(R: Int, C: Int, map: Array<Array<Char>>, visited: Array<Boolean>, x: Int, y: Int, depth: Int): Int {
    return (0 until 4).maxOf {
        val nextY = y + dy[it]
        val nextX = x + dx[it]
        if (nextY in 0 until R && nextX in 0 until C) {
            val alphaIdx = getAlphaIndex(map[nextY][nextX])
            if (visited[alphaIdx]) {
                depth
            } else {
                visited[alphaIdx] = true
                val result = dfs(R, C, map, visited, nextX, nextY, depth + 1)
                visited[alphaIdx] = false
                result
            }
        } else depth
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (R, C) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Char>>(R) { Array(C) { ' ' } }
    for (y in 0 until R) {
        val line = reader.readLine()
        for (x in 0 until C) {
            map[y][x] = line[x]
        }
    }
    val visited = Array<Boolean>(26) { false }
    visited[getAlphaIndex(map[0][0])] = true
    println(dfs(R, C, map, visited, 0, 0, 1))
}