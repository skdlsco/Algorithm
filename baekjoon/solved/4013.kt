package `4013`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.min

data class Vertex(val id: Int, var isRestaurant: Boolean = false, var money: Int)

class Solution(val V: Int, val graph: Array<ArrayList<Vertex>>, val vertexArr: Array<Vertex>) {
    val st = Stack<Vertex>()
    val sccId = Array<Int>(V + 1) { -1 }
    val sccList = ArrayList<Vertex>()
    val discoveredId = Array<Int>(V + 1) { -1 }
    var sccCounter = 0
    var vertexCounter = 0

    fun scc(here: Vertex): Int {
        var ret = vertexCounter
        discoveredId[here.id] = vertexCounter
        vertexCounter++
        st.add(here)
        for (there in graph[here.id]) {
            if (discoveredId[there.id] == -1)
                ret = min(ret, scc(there))
            else if (sccId[there.id] == -1)
                ret = min(ret, discoveredId[there.id])
        }
        if (ret == discoveredId[here.id]) {
            val newScc = Vertex(sccCounter, false, 0)
            sccList.add(newScc)
            while (true) {
                val t = st.pop()
                newScc.isRestaurant = newScc.isRestaurant || t.isRestaurant
                newScc.money += t.money
                sccId[t.id] = sccCounter
                if (t == here)
                    break
            }
            sccCounter++
        }
        return ret
    }

    fun tarjanScc(start: Int): Array<ArrayList<Int>> {
        scc(vertexArr[start])
        val newGraph = Array<ArrayList<Int>>(sccCounter) { ArrayList() }
        for (i in 1..V) {
            val group = sccId[i]
            for (there in graph[i]) {
                val thereGroup = sccId[there.id]
                if (thereGroup < group)
                    newGraph[group].add(thereGroup)
            }
        }
        return newGraph
    }

    fun solve(start: Int): Int {
        // scc 추출
        val tarjanGraph = tarjanScc(start)
        val dp = Array<Int>(sccCounter) { sccList[it].money }
        for (i in tarjanGraph.indices.reversed()) {
            for (next in tarjanGraph[i]) {
                dp[next] = max(dp[next], dp[i] + sccList[next].money)
            }
        }
        var result = 0
        for (i in 0 until sccCounter) {
            if (sccList[i].isRestaurant)
                result = max(result, dp[i])
        }
        return result
    }
}

class TokenReader(private val reader: BufferedReader) {
    private var tokenizer = StringTokenizer(reader.readLine())
    fun nextInt(): Int {
        while (!tokenizer.hasMoreTokens())
            tokenizer = StringTokenizer(reader.readLine())
        return tokenizer.nextToken().toInt()
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val tReader = TokenReader(reader)
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = tReader.nextInt()
    val M = tReader.nextInt()
    val vertexArr = Array<Vertex>(N + 1) { Vertex(it, false, 0) }
    val graph = Array<ArrayList<Vertex>>(N + 1) { ArrayList() }
    // 간선 정보
    repeat(M) {
        val u = tReader.nextInt()
        val v = tReader.nextInt()
        graph[u].add(vertexArr[v])
    }
    // ATM현금
    repeat(N) {
        val money = tReader.nextInt()
        vertexArr[it + 1].money += money
    }
    // 시작점, 음식점 수
    val S = tReader.nextInt()
    val P = tReader.nextInt()
    // 음식점 업데이트
    repeat(P) {
        val restaurant = tReader.nextInt()
        vertexArr[restaurant].isRestaurant = true
    }
    val solution = Solution(N, graph, vertexArr)
    val result = solution.solve(S)
    writer.write("${result}\n")
    writer.flush()
}