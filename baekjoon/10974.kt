package `10974`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val writer = BufferedWriter(OutputStreamWriter(System.out))
fun f(N: Int, visited: BooleanArray, arr: IntArray, depth: Int) {
    if (depth == N) {
        writer.write("${arr.joinToString(" ")}\n")
        return
    }
    for (i in 1..N) {
        if (!visited[i]) {
            visited[i] = true
            arr[depth] = i
            f(N, visited, arr, depth +  1)
            visited[i] = false
        }

    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val visited = BooleanArray(N + 1) { false }
    visited[0] = true
    f(N, visited, IntArray(N) { 0 }, 0)
    writer.flush()
    writer.close()
}