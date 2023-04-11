package `13023`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun findABCDE(friendsList: Array<LinkedList<Int>>, visited: Array<Boolean>, now: Int, depth: Int): Boolean {
    if (depth >= 5)
        return true
    for (next in friendsList[now]) {
        if (!visited[next]) {
            visited[next] = true
            if (findABCDE(friendsList, visited, next, depth + 1))
                return true
            visited[next] = false
        }
    }
    return false
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val friendsList = Array<LinkedList<Int>>(N + 1) { LinkedList() }
    val visited = Array<Boolean>(N + 1) { false }
    repeat(M) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        friendsList[a].add(b)
        friendsList[b].add(a)
    }
    var isExist = false
    for (i in 1..N) {
        visited[i] = true
        if (findABCDE(friendsList, visited, i, 1)) {
            isExist = true
            break
        }
        visited[i] = false
    }
    writer.write("${if (isExist) 1 else 0}")
    writer.flush()
}