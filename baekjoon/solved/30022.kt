package `30022`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var (N, A, B) = reader.readLine().split(" ").map { it.toInt() }
    val item = Array<Pair<Long, Long>>(N.toInt()) { Pair(0, 0) }
    repeat(N.toInt()) {
        val (p, q) = reader.readLine().split(" ").map { it.toLong() }
        item[it] = Pair(p, q)
    }
    item.sortByDescending { abs(it.first - it.second) }
    var sum = 0L
    for ((a, b) in item) {
        if (A == 0)
            sum += b
        else if (B == 0)
            sum += a
        else {
            if (a > b) {
                B--
                sum += b
            } else {
                A--
                sum += a
            }
        }
    }
    writer.write("${sum}\n")
    writer.flush()
}