package `1774`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayList
import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

data class Pos(val x: Double, val y: Double)

fun find(set: Array<Int>, node: Int): Int {
    if (set[node] == node)
        return node
    set[node] = find(set, set[node])
    return set[node]
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Pos>(N + 1) { Pos(0.0, 0.0) }
    val set = Array<Int>(N + 1) { it }
    repeat(N) {
        val (x, y) = reader.readLine().split(" ").map { it.toDouble() }
        arr[it + 1] = Pos(x, y)
    }

    repeat(M) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        val aP = find(set, a)
        val bP = find(set, b)
        if (aP != bP) {
            set[bP] = aP
        }
    }
    val edges = ArrayList<Pair<Double, Pair<Int, Int>>>()
    for (i in 1 until N) {
        for (j in i + 1..N) {
            val dis = sqrt((arr[i].x - arr[j].x).pow(2) + (arr[i].y - arr[j].y).pow(2))
            edges.add(Pair(dis, Pair(i, j)))
        }
    }
    edges.sortBy { it.first }
    var sum = 0.0
    for (edge in edges) {
        val aP = find(set, edge.second.first)
        val bP = find(set, edge.second.second)
        if (aP != bP) {
            set[bP] = aP
            sum += edge.first
        }
    }
    writer.write("%.2f".format(sum))
    writer.flush()
}
    