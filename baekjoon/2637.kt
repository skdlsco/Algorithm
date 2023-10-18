package `2637`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val graph = Array<ArrayList<Pair<Int, Int>>>(101) { ArrayList() }
    // [i][j] = k, i를 만드는데 소모되는 기본재료 j의 개수 = k
    val blueprint = Array<Array<Int>>(101) { Array(101) { 0 } }
    val N = reader.readLine().toInt()
    val M = reader.readLine().toInt()
    val inDegreeArr = Array<Int>(101) { 0 }
    repeat(M) {
        val (X, Y, K) = reader.readLine().split(" ").map { it.toInt() }
        graph[Y].add(Pair(X, K))
        inDegreeArr[X]++
    }

    val queue = LinkedList<Int>()
    for (i in 1..N) {
        if (inDegreeArr[i] == 0) {
            queue.add(i)
            blueprint[i][i] = 1
        }
    }
    while (queue.isNotEmpty()) {
        val cur = queue.pop()
        for ((next, K) in graph[cur]) {
            for (i in 1..N) {
                blueprint[next][i] += blueprint[cur][i] * K
            }
            inDegreeArr[next]--
            if (inDegreeArr[next] == 0)
                queue.add(next)
        }
    }
    for (i in 1..N) {
        if (blueprint[N][i] > 0)
            writer.write("$i ${blueprint[N][i]}\n")
    }
    writer.flush()
}