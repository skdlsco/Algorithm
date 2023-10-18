package `16437`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer
import kotlin.math.max

fun getSum(
    graph: Array<LinkedList<Int>>,
    sheep: Array<Long>,
    wolf: Array<Long>,
    cur: Int
): Long {
    val curSheep = sheep[cur] + graph[cur].sumOf {
        getSum(graph, sheep, wolf, it)
    }
    return max(curSheep - wolf[cur], 0)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val graph = Array<LinkedList<Int>>(N + 1) { LinkedList() }
    val sheep = Array<Long>(N + 1) { 0L }
    val wolf = Array<Long>(N + 1) { 0L }
    for (i in 2..N) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        val type = stringTokenizer.nextToken()
        val amount = stringTokenizer.nextToken().toLong()
        val bridge = stringTokenizer.nextToken().toInt()
        if (type == "S")
            sheep[i] = amount
        else
            wolf[i] = amount
        graph[bridge].add(i)
    }
    println(getSum(graph, sheep, wolf, 1))
}