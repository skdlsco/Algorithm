package `10971`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun isVisited(visited: IntArray, depth: Int, dest: Int): Boolean {
    repeat((0 until depth).count()) {
        if (visited[it] == dest)
            return true
    }
    return false
}

fun f(N: Int, map: Array<Array<Int>>, visited: IntArray, depth: Int, sum: Int): Int {
    if (N == depth) {
        val last = visited.last()
        val first = visited.first()
        return if (map[last][first] != 0)
            sum + map[last][first]
        else
            Int.MAX_VALUE
    }
    val prev = visited[depth - 1]
    return (0 until N).minOf {
        val isVisited = isVisited(visited, depth, it)
        if (map[prev][it] != 0 && !isVisited) {
            visited[depth] = it
            f(N, map, visited, depth + 1, sum + map[prev][it])
        } else
            Int.MAX_VALUE
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val map = Array<Array<Int>>(N) { reader.readLine().split(" ").map { it.toInt() }.toTypedArray() }

    val visited = IntArray(N) { -1 }
    val result = (0 until N).minOf {
        visited[0] = it
        f(N, map, visited, 1, 0)
    }
    println(result)
}