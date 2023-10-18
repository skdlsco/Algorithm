package `1351`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeMap

val map = TreeMap<Long, Long>()

fun find(n: Long, P: Long, Q: Long): Long {
    if (map.contains(n))
        return map[n]!!
    if (n == 0L)
        return 1L
    map[n] = find(n / P, P, Q) + find(n / Q, P, Q)
    return map[n]!!
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, P, Q) = reader.readLine().split(" ").map { it.toLong() }

    val ans = find(N, P, Q)
    writer.write("${ans}\n")
    writer.flush()
}
