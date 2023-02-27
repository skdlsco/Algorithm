package `2001`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

data class Info(val pos: Int, val weight: Int, val jem: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, k) = reader.readLine().split(" ").map { it.toInt() }
    // 보석이 있는 섬
    val island = Array<Int>(n + 1) { -1 }
    // 특정 보석, 섬 번호
    val visited = Array<Array<Boolean>>(1 shl k) { Array(n + 1) { false } }
    // start,(end,weight)
    val map = Array<LinkedList<Pair<Int, Int>>>(n + 1) { LinkedList() }
    repeat(k) {
        val pos = reader.readLine().toInt()
        island[pos] = it
    }
    repeat(m) {
        val (a, b, c) = reader.readLine().split(" ").map { it.toInt() }
        map[a].push(Pair(b, c))
        map[b].push(Pair(a, c))
    }
    val queue: Queue<Info> = LinkedList<Info>()
    queue.add(Info(1, 0, 0))
    visited[0][1] = true
    while (queue.isNotEmpty()) {
        val (pos, weight, jem) = queue.remove()

        // 보석을 줍다.
        if (island[pos] != -1) {
            val nextJem = jem or (1 shl island[pos])
            if (!visited[nextJem][pos]) {
                queue.add(Info(pos, weight + 1, nextJem))
                visited[nextJem][pos] = true
            }
        }
        map[pos].forEach {
            if (weight <= it.second && !visited[jem][it.first]) {
                queue.add(Info(it.first, weight, jem))
                visited[jem][it.first] = true
            }
        }
    }
    var result = 0
    for (i in 0 until (1 shl k)) {
        if (visited[i][1])
            result = max(result, i.countOneBits())
    }
    println(result)
}