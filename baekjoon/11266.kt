import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

var count = 0

fun dfs(graph: Array<ArrayList<Int>>, ans: MutableSet<Int>, visited: Array<Int>, cur: Int, isRoot: Boolean): Int {
    var back = count
    visited[cur] = count++
    var childCnt = 0
    for (next in graph[cur]) {
        if (visited[next] == -1) {
            val res = dfs(graph, ans, visited, next, false)
            if (res >= visited[cur])
                ans.add(cur)
            back = minOf(back, res)
            childCnt++
        } else {
            back = minOf(back, visited[next])
        }
    }
    if (isRoot) {
        if (childCnt > 1) ans.add(cur)
        else
            ans.remove(cur)
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
    val ans = HashSet<Int>()
    val visited = Array<Int>(V + 1) { -1 }
    for (i in 1..V) {
        if (visited[i] == -1)
            dfs(graph, ans, visited, i, true)
    }
    writer.write("${ans.size}\n")
    writer.write(ans.sorted().joinToString(" "))
    writer.flush()
}