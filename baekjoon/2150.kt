package `2150`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack
import kotlin.math.min


class TarjanScc(val V: Int, val E: Int, val graph: Array<ArrayList<Int>>) {
    val st = Stack<Int>()
    val sccId = Array<Int>(V + 1) { -1 }
    val discoveredId = Array<Int>(V + 1) { -1 }
    var sccCounter = 0
    var vertexCounter = 0

    fun scc(here: Int): Int {
        var ret = vertexCounter
        discoveredId[here] = vertexCounter
        vertexCounter++
        st.add(here)
        for (there in graph[here]) {
            // there이 트리간선
            if (discoveredId[there] == -1)
                ret = min(ret, scc(there))
            // 무시하면 안되는 교차간선
            else if (sccId[there] == -1)
                ret = min(ret, discoveredId[there])
        }
        // 부모로 올라가기 전에 처리(자식에서 무시하면 안되는 교차간선이 발견되지 않은경우인것같다)
        if (ret == discoveredId[here]) {
            while (true) {
                val t = st.pop()
                sccId[t] = sccCounter
                if (t == here)
                    break
            }
            sccCounter++
        }
        return ret
    }

    fun tarjanScc(): Array<ArrayList<Int>> {
        for (v in 1..V) {
            if (discoveredId[v] == -1) scc(v)
        }
        val newGraph = Array<ArrayList<Int>>(sccCounter) { ArrayList() }
        for (i in 1..V) {
            val group = sccId[i]
            newGraph[group].add(i)
        }
        return newGraph
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (V, E) = reader.readLine().split(" ").map { it.toInt() }
    val graph = Array<ArrayList<Int>>(V + 1) { ArrayList() }
    repeat(E) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() }
        graph[u].add(v)
    }
    val tarjan = TarjanScc(V, E, graph)
    val newGraph = tarjan.tarjanScc()

    newGraph.forEach { it.sort() }
    newGraph.sortBy { it.first() }
    writer.write("${newGraph.size}\n")
    for (group in newGraph) {
        writer.write("${group.joinToString(" ")} -1")
        writer.newLine()
    }
    writer.flush()
}