import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (V, E) = reader.readLine().split(" ").map { it.toInt() }
    val K = reader.readLine().toInt() - 1
    val graph = Array<ArrayList<Pair<Int, Int>>>(V) { ArrayList() }
    val dist = Array<Int>(V) { Int.MAX_VALUE }

    repeat(E) {
        val (u, v, w) = reader.readLine().split(" ").map { it.toInt() }
        graph[u - 1].add(Pair(v - 1, w))
    }
    dist[K] = 0
    val queue = PriorityQueue<Pair<Int, Int>> { o1, o2 -> dist[o1.first] - dist[o2.first] }
    queue.add(Pair(K, 0))
    while (queue.isNotEmpty()) {
        val (n, w) = queue.remove()
        graph[n].forEach {
            if (dist[it.first] > dist[n] + it.second) {
                dist[it.first] = dist[n] + it.second
                queue.add(it)
            }
        }
    }
    dist.forEach {
        println("${if (it == Int.MAX_VALUE) "INF" else it}")
    }
}