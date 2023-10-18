package `26267`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val map = HashMap<Long, Long>()
    repeat(N) {
        val (x, t, c) = reader.readLine().split(" ").map { it.toLong() }
        map[x - t] = (map[x - t] ?: 0) + c
    }
    val ans = map.maxOf { it.value }
    writer.write("${ans}\n")
    writer.flush()
}

