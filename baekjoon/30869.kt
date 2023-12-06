import java.io.*
import java.util.PriorityQueue
import kotlin.math.min

data class Edge(val node: Int, val t: Int, val g: Int)
data class Data(val node: Int, val cost: Int, val k: Int)

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Edge>>(N + 1) { ArrayList() }
    val simpleGraph = Array<Array<Boolean>>(N + 1) { Array(N + 1) { false } }
    repeat(M) {
        val (s, e, t, g) = reader.readLine().split(" ").map { it.toInt() }
        if (s == N || e == 1)
            return@repeat
        simpleGraph[s][e] = true
        graph[s].add(Edge(e, t, g))
    }

    val dist = Array<Array<Int>>(N + 1) { Array(K + 2) { Int.MAX_VALUE } }
    dist[1][0] = 0
    val pq = PriorityQueue<Data>() { o1, o2 ->
        o1.cost.compareTo(o2.cost)
    }
    pq.add(Data(1, 0, 0))
    while (pq.isNotEmpty()) {
        val (cur, cost, k) = pq.remove()
        if (dist[cur][k] < cost)
            continue
        for ((next, t, g) in graph[cur]) {
            // 기다리기
            var nextCost = cost + t + (g - cost % g) % g
            if (dist[next][k] > nextCost && (k == 0 || dist[next][k - 1] > nextCost)) {
                dist[next][k] = nextCost
                pq.add(Data(next, nextCost, k))
            }
            // 빨리 기다리기
            nextCost = cost + t
            if (k < K && dist[next][k + 1] > nextCost && dist[next][k] > nextCost) {
                dist[next][k + 1] = nextCost
                pq.add(Data(next, nextCost, k + 1))
            }
            dist[next][k + 1] = min(dist[next][k + 1], dist[next][k])
        }
    }
    val ans = dist[N].minOf { it }
    if (ans == Int.MAX_VALUE)
        writer.write("-1\n")
    else
        writer.write("${dist[N].minOf { it }}\n")
    writer.flush()
}