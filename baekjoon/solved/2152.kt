package `2152`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import java.util.Stack
import kotlin.math.min

val graph = Array<ArrayList<Int>>(10101) { ArrayList() }
val num = Array<Int>(10101) { -1 }
val sccNum = Array<Int>(10101) { -1 }
var sccCounter = 0
var nodeCounter = 0
val sccList = ArrayList<ArrayList<Int>>()
val st = Stack<Int>()

fun dfs(here: Int): Int {
    var low = nodeCounter
    num[here] = nodeCounter
    nodeCounter++
    st.add(here)
    for (there in graph[here]) {
        if (num[there] == -1) {
            low = min(low, dfs(there))
        } else if (sccNum[there] == -1) {
            low = min(low, num[there])
        }
    }
    if (num[here] == low) {
        val group = ArrayList<Int>()
        while (st.isNotEmpty()) {
            val cur = st.pop()
            sccNum[cur] = sccCounter
            group.add(cur)
            if (cur == here)
                break
        }
        sccList.add(group)
        sccCounter++
    }
    return low
}

fun tarjanScc(N: Int): List<List<Int>> {
    for (i in 1..N) {
        if (num[i] == -1)
            dfs(i)
    }
    val newGraph = Array<ArrayList<Int>>(sccCounter) { ArrayList() }
    for (i in 1..N) {
        val group = sccNum[i]
        for (next in graph[i]) {
            val nextGroup = sccNum[next]
            if (group != nextGroup)
                newGraph[group].add(nextGroup)
        }
    }
    return newGraph.toList()
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, S, T) = reader.readLine().split(" ").map { it.toInt() }
    repeat(M) {
        val (a, b) = reader.readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
    }

    val compressedGraph = tarjanScc(N)
    val pq = PriorityQueue<Pair<Int, Int>>() { o1, o2 -> o2.first - o1.first }
    val arr = Array<Int>(compressedGraph.size) { 0 }

    arr[sccNum[S]] = sccList[sccNum[S]].size
    pq.add(Pair(sccNum[S], sccList[sccNum[S]].size))

    while (pq.isNotEmpty()) {
        val (here, cost) = pq.remove()
        if (arr[here] > cost)
            continue
        for (there in compressedGraph[here]) {
            val nextCost = cost + sccList[there].size
            if (arr[there] < nextCost) {
                arr[there] = nextCost
                pq.add(Pair(there, nextCost))
            }
        }
    }
    writer.write("${arr[sccNum[T]]}\n")
    writer.flush()
}