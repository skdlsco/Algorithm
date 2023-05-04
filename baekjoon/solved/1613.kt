package `1613`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

// 400 * 400 -> [a][b] a>b 이면 1 a<b 이면 -1 알 수 없음 0
// 초기 0으로 초기화
// 진입 차수 개수 순으로 업데이트
// 다음 노드의 관계에 현재 노드의 관계를 업데이트
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val inDegreeArr = Array<Int>(N + 1) { 0 }
    val graph = Array<Queue<Int>>(N + 1) { LinkedList<Int>() }
    val table = Array<Array<Int>>(N + 1) { Array(N + 1) { 0 } }

    repeat(K) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() }
        graph[u].add(v)
        inDegreeArr[v]++
    }
    val queue: Queue<Int> = LinkedList()
    for (i in 1..N) {
        if (inDegreeArr[i] == 0)
            queue.add(i)
    }
    while (queue.isNotEmpty()) {
        val cur = queue.remove()
        val nextMap = Array<Int>(N + 1) { table[cur][it] }
        for (next in graph[cur]) {
            table[cur][next] = -1
            table[next][cur] = 1
            inDegreeArr[next]--
            for (i in 1..N) {
                if (cur == i || next == i)
                    continue
                if (nextMap[i] == 0)
                    continue
                table[next][i] = nextMap[i]
                table[i][next] = -nextMap[i]
            }
            if (inDegreeArr[next] == 0)
                queue.add(next)
        }
    }
    val S = reader.readLine().toInt()
    repeat(S) {
        val (u, v) = reader.readLine().split(" ").map { it.toInt() }
        writer.write("${table[u][v]}\n")
    }
    writer.flush()
}