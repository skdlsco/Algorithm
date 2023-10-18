package `13911`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

class TokenReader(private val reader: BufferedReader) {
    private var tokenizer = StringTokenizer(reader.readLine())

    fun nextToken(): String {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = StringTokenizer(reader.readLine())
        }
        return tokenizer.nextToken()
    }

    fun nextInt(): Int {
        return nextToken().toInt()
    }
}


fun dij(graph: Array<ArrayList<Pair<Int, Int>>>, startList: List<Int>, dist: Array<Int>) {
    val queue = PriorityQueue<Pair<Int, Int>>() { o1, o2 ->
        o1.second - o2.second
    }
    startList.forEach {
        queue.add(Pair(it, 0))
        dist[it] = 0
    }
    while (queue.isNotEmpty()) {
        val (cur, distance) = queue.remove()
        if (dist[cur] < distance)
            continue
        for ((next, cost) in graph[cur]) {
            val nextDistance = cost + distance
            if (nextDistance < dist[next]) {
                dist[next] = nextDistance
                queue.add(Pair(next, nextDistance))
            }
        }
    }
}

fun main() {
    val reader = TokenReader(BufferedReader(InputStreamReader(System.`in`)))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val V = reader.nextInt()
    val E = reader.nextInt()
    val graph = Array<ArrayList<Pair<Int, Int>>>(V + 1) { ArrayList() }
    repeat(E) {
        val u = reader.nextInt()
        val v = reader.nextInt()
        val w = reader.nextInt()
        graph[u].add(Pair(v, w))
        graph[v].add(Pair(u, w))
    }
    val type = Array<Char>(V + 1) { 'H' }
    val mcDist = Array<Int>(V + 1) { Int.MAX_VALUE }
    val sbDist = Array<Int>(V + 1) { Int.MAX_VALUE }
    val M = reader.nextInt()
    val x = reader.nextInt()
    val mcList = (0 until M).map { reader.nextInt() }
    val S = reader.nextInt()
    val y = reader.nextInt()
    val sbList = (0 until S).map { reader.nextInt() }
    mcList.forEach {
        type[it] = 'M'
    }
    sbList.forEach {
        type[it] = 'S'
    }
    dij(graph, mcList, mcDist)
    dij(graph, sbList, sbDist)
    var ans = Int.MAX_VALUE
    for (i in 1..V) {
        if (type[i] != 'H')
            continue
        if (mcDist[i] <= x && sbDist[i] <= y)
            ans = min(ans, mcDist[i] + sbDist[i])
    }
    if (ans == Int.MAX_VALUE)
        ans = -1
    writer.write("${ans}\n")
    writer.flush()
}