package `29723`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeMap

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val map = TreeMap<String, Int>()
    repeat(N) {
        val (s, p) = reader.readLine().split(" ")
        map[s] = p.toInt()
    }
    var sum = 0
    repeat(K) {
        val s = reader.readLine()
        sum += map[s]!!
        map.remove(s)
    }
    val arr = map.toList().sortedBy { it.second }
    var max = sum
    var min = sum
    for (i in 0 until M - K) {
        min += arr[i].second
        max += arr[arr.size - i - 1].second
    }
    writer.write("${min} ${max}")
    writer.flush()
}
    