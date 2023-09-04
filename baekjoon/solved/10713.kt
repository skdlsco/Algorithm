package `10713`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Int>(N + 1) { 0 }
    val plan = reader.readLine().split(" ").map { it.toInt() }
    var prev = plan[0]
    for (i in 1 until M) {
        val cur = plan[i]
        arr[min(cur, prev)]++
        arr[max(cur, prev)]--
        prev = cur
    }
    var sum = 0L
    var cnt = 0
    for (i in 1 until N) {
        cnt += arr[i]
        val (A, B, C) = reader.readLine().split(" ").map { it.toLong() }
        if (A * cnt < B * cnt + C)
            sum += A * cnt
        else
            sum += B * cnt + C
    }
    writer.write("${sum}\n")
    writer.flush()
}
