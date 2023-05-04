package `1647`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class DisjointSet(val N: Int) {
    val parent = Array<Int>(N + 1) { it }

    // u -> v
    fun merge(u: Int, v: Int) {
        val uRoot = find(u)
        val vRoot = find(v)
        if (uRoot == vRoot)
            return
        parent[uRoot] = vRoot
    }

    fun find(node: Int): Int {
        if (isRoot(node))
            return node
        val root = find(parent[node])
        parent[node] = root
        return root
    }

    fun isRoot(node: Int): Boolean {
        return parent[node] == node
    }
}

data class Road(val u: Int, val v: Int, val cost: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val edges = Array<Road>(M) { Road(0, 0, 0) }
    repeat(M) {
        val (u, v, c) = reader.readLine().split(" ").map { it.toInt() }
        edges[it] = Road(u, v, c)
    }
    edges.sortBy { it.cost }
    val selectedEdges = ArrayList<Road>()
    val connectedNode = DisjointSet(N)
    edges.forEach {
        val uRoot = connectedNode.find(it.u)
        val vRoot = connectedNode.find(it.v)
        if (uRoot == vRoot)
            return@forEach
        selectedEdges.add(it)
        connectedNode.merge(it.u, it.v)
    }
    val result = selectedEdges.sumOf { it.cost } - selectedEdges.maxOf { it.cost }
    writer.write("${result}\n")
    writer.flush()
}