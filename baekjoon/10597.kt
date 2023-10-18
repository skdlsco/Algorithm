package `10597`

import java.io.BufferedReader
import java.io.InputStreamReader

fun lengthToN(len: Int): Int =
    if (len < 10)
        len
    else 9 + (len - 9) / 2

fun recover(line: String, arr: Array<Int>, visited: Array<Boolean>, depth: Int, idx: Int): Boolean {
    if (line.length <= idx)
        return true
    var isSuccess = false
    val n1 = line[idx].digitToInt()
    if (n1 != 0 && !visited[n1]) {
        visited[n1] = true
        arr[depth] = n1
        isSuccess = recover(line, arr, visited, depth + 1, idx + 1)
        visited[n1] = false
    }
    if (!isSuccess && idx + 1 < line.length) {
        val n2 = line.slice(idx..idx + 1).toInt()
        if (n2 in 10 until visited.size && !visited[n2]) {
            visited[n2] = true
            arr[depth] = n2
            isSuccess = recover(line, arr, visited, depth + 1, idx + 2)
            visited[n2] = false
        }
    }
    return isSuccess
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val line = reader.readLine()
    val N = lengthToN(line.length)
    val visited = Array<Boolean>(N + 1) { false }
    val arr = Array<Int>(N) { 0 }
    recover(line, arr, visited, 0, 0)
    println(arr.joinToString(" "))
}