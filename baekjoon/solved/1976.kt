package `1976`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val set = Array<Int>(201) { it }

fun union(a: Int, b: Int) {
    val aRoot = findRoot(a)
    val bRoot = findRoot(b)
    if (aRoot == bRoot)
        return
    set[bRoot] = aRoot
}

fun findRoot(node: Int): Int {
    if (set[node] == node)
        return node
    set[node] = findRoot(set[node])
    return set[node]
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val M = reader.readLine().toInt()
    for (y in 1..N) {
        val connected = reader.readLine().split(" ").map { it == "1" }
        for (x in 0  until N) {
            if (connected[x])
                union(y, x + 1)
        }
    }

    var isPossible = true
    if (M > 0) {
        val route = reader.readLine().split(" ").map { it.toInt() }
        val start = route.first()
        val startRoot = findRoot(start)
        for (i in 1 until route.size) {
            if (findRoot(route[i]) != startRoot) {
                isPossible = false
                break
            }
        }
    }
    if (isPossible)
        writer.write("YES\n")
    else
        writer.write("NO\n")
    writer.flush()
}