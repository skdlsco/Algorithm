package `28707`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import java.util.TreeMap

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val start = reader.readLine().split(" ").map { it.toInt() - 1 }.joinToString("")
    val visited = TreeMap<String, Int>()
    val M = reader.readLine().toInt()
    val commands = ArrayList<Pair<Pair<Int, Int>, Int>>()
    repeat(M) {
        val (idx1, idx2, cost) = reader.readLine().split(" ").map { it.toInt() }
        commands.add(Pair(Pair(idx1 - 1, idx2 - 1), cost))
    }
    var ans = -1
    val pq = PriorityQueue<Pair<String, Int>>() { o1, o2 ->
        o1.second - o2.second
    }
    visited[start] = 0
    pq.add(Pair(start, 0))
    val end = start.toCharArray().sorted().joinToString("")
    while (pq.isNotEmpty()) {
        val (s, cur) = pq.remove()
        if ((visited[s] ?: Int.MAX_VALUE) < cur)
            continue
        if (s == end) {
            ans = cur
            break
        }
        for (c in commands) {
            val temp = s.toCharArray()
            temp[c.first.first] = s[c.first.second]
            temp[c.first.second] = s[c.first.first]
            val ns = temp.concatToString()
            if ((visited[ns] ?: Int.MAX_VALUE) > c.second + cur) {
                pq.add(Pair(ns, cur + c.second))
                visited[ns] = cur + c.second
            }
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}