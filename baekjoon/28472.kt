import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

fun init(graph: Array<ArrayList<Int>>, arr: Array<Int>, cur: Int, prev: Int, depth: Int) {
    if (graph[cur].size == 1 && prev != 0)
        return

    for (next in graph[cur]) {
        if (next != prev) {
            init(graph, arr, next, cur, depth + 1)
        }
    }
    if (depth % 2 == 0) {
        arr[cur] = graph[cur].maxOf {
            if (it == prev)
                0
            else
                arr[it]
        }
    } else {
        arr[cur] = graph[cur].minOf {
            if (it == prev)
                Int.MAX_VALUE
            else
                arr[it]
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, R) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Int>(N + 1) { -1 }
    val graph = Array<ArrayList<Int>>(N + 1) { ArrayList() }
    repeat(N - 1) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }
    val L = reader.readLine().toInt()
    repeat(L) {
        val (k, t) = reader.readLine().split(" ").map { it.toInt() }
        arr[k] = t
    }
    init(graph, arr, R, 0, 0)
    val Q = reader.readLine().toInt()
    repeat(Q) {
        val q = reader.readLine().toInt()
        writer.write("${arr[q]}\n")
    }
    writer.flush()
}