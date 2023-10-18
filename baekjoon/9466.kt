package `9466`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

val visited = BooleanArray(100001)
val graph = IntArray(100001)
val group = IntArray(100001)

fun dfs(here: Int): Int {
    visited[here] = true
    val there = graph[here]
    var back = 0
    // 이번 탐색에서 방문한 적이 있는경우
    // there부터 here까지 사이클
    if (visited[there])
        back = there
    else if (group[there] == 0) {
        back = dfs(there)
    }
    if (back != 0)
        group[here] = back
    if (back == here)
        back = 0
    if (group[here] == 0)
        group[here] = -1
    visited[here] = false
    return back
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val t = reader.readLine().toInt()

    repeat(t) {
        val n = reader.readLine().toInt()
        val tokenizer = StringTokenizer(reader.readLine())
        for (i in 1..n) {
            group[i] = 0
            graph[i] = tokenizer.nextToken().toInt()
        }
        for (i in 1..n) {
            if (group[i] == 0)
                dfs(i)
        }
        val result = (1..n).count { group[it] == -1 }
        writer.write("${result}\n")
    }
    writer.flush()
}