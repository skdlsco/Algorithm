import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

var count = 0

fun dfs(graph: Array<ArrayList<Int>>, ans: ArrayList<Pair<Int, Int>>, visited: Array<Int>, cur: Int, parent: Int): Int {
    var back = count
    visited[cur] = count++
    for (next in graph[cur]) {
        if (next == parent)
            continue
        if (visited[next] == -1) {
            val res = dfs(graph, ans, visited, next, cur)
            if (res > visited[cur])
                ans.add(Pair(minOf(cur, next), maxOf(cur, next)))
            back = minOf(back, res)
        } else {
            back = minOf(back, visited[next])
        }
    }
    return back
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (V, E) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Int>>(V + 1) { ArrayList() }
    repeat(E) {
        val (A, B) = reader.readLine().split(" ").map { it.toInt() }
        graph[A].add(B)
        graph[B].add(A)
    }
    val ans = ArrayList<Pair<Int, Int>>()
    val visited = Array<Int>(V + 1) { -1 }
    for (i in 1..V) {
        if (visited[i] == -1)
            dfs(graph, ans, visited, i, 0)
    }
    ans.sortWith() { o1, o2 ->
        if (o1.first == o2.first)
            o1.second.compareTo(o2.second)
        else
            o1.first.compareTo(o2.first)

    }
    writer.write("${ans.size}\n")
    writer.write(ans.joinToString("\n") { "${it.first} ${it.second}" })
    writer.flush()
}