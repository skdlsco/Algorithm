package `4195`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeMap

fun find(network: Array<Int>, cur: Int): Int {
    if (network[cur] == cur)
        return cur
    val root = find(network, network[cur])
    network[cur] = root
    return root
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()

    repeat(T) {
        val F = reader.readLine().toInt()
        val map = TreeMap<String, Int>()
        val network = Array<Int>(F * 2 + 1) { it }
        val cnt = Array<Int>(F * 2 + 1) { 1 }
        repeat(F) {
            val (a, b) = reader.readLine().split(" ")
            if (!map.contains(a))
                map[a] = map.size
            if (!map.contains(b))
                map[b] = map.size
            val aRoot = find(network, map[a]!!)
            val bRoot = find(network, map[b]!!)
            if (aRoot != bRoot) {
                cnt[aRoot] += cnt[bRoot]
                network[bRoot] = aRoot
            }
            writer.write("${cnt[aRoot]}\n")
        }
    }
    writer.flush()
}