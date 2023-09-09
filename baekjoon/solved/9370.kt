package `9370`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.abs

data class Data(val a: Int, val dist: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val (n, m, t) = reader.readLine().split(" ").map { it.toInt() }
        val (s, g, h) = reader.readLine().split(" ").map { it.toInt() }
        val graph = Array<ArrayList<Pair<Int, Int>>>(n + 1) { ArrayList() }
        repeat(m) {
            val (a, b, d) = reader.readLine().split(" ").map { it.toInt() }
            graph[a].add(Pair(b, d))
            graph[b].add(Pair(a, d))
        }
        val isTarget = Array<Boolean>(n + 1) { false }
        repeat(t) {
            val target = reader.readLine().toInt()
            isTarget[target] = true
        }

        val visited = Array<Int>(n + 1) { Int.MAX_VALUE }
        val pq = PriorityQueue<Data> { o1, o2 -> o1.dist - o2.dist }
        visited[s] = 0
        pq.add(Data(s, 0))
        while (pq.isNotEmpty()) {
            val (node, dist) = pq.remove()
            if (visited[node] < dist)
                continue
            for ((next, d) in graph[node]) {
                val nextD = dist + d
                if (visited[next] > nextD) {
                    pq.add(Data(next, nextD))
                    visited[next] = nextD
                }
            }
        }

        val passVisited = Array<Int>(n + 1) { Int.MAX_VALUE }
        val secondS = if (visited[g] > visited[h]) g else h
        passVisited[secondS] = 0
        pq.add(Data(secondS, 0))
        while (pq.isNotEmpty()) {
            val (node, dist) = pq.remove()
            if (passVisited[node] < dist)
                continue
            for ((next, d) in graph[node]) {
                val nextD = dist + d
                if (passVisited[next] > nextD) {
                    pq.add(Data(next, nextD))
                    passVisited[next] = nextD
                }
            }
        }
        val ans = (1..n).filter { isTarget[it] && visited[it] == visited[secondS] + passVisited[it] }
        writer.write(ans.sorted().joinToString(" "))
        writer.newLine()
    }
    writer.flush()
}