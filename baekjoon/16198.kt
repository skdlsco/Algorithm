package `16198`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun backtracking(N: Int, weightArr: Array<Int>, visited: Array<Boolean>): Int {
    if (visited.all { it })
        return 0
    return (1 until N - 1).maxOf {
        if (!visited[it]) {
            val front = weightArr[(it - 1 downTo 0).first { !visited[it] }]
            val back = weightArr[(it + 1 until N).first { !visited[it] }]
            visited[it] = true
            val result = front * back + backtracking(N, weightArr, visited)
            visited[it] = false
            result
        } else
            0
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val weightArr = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    val visited = Array<Boolean>(N) { false }

    writer.write("${backtracking(N, weightArr, visited)}\n")
    writer.flush()
}