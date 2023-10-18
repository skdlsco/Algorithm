package `29727`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toLong()
    var (ax, ay) = reader.readLine().split(" ").map { it.toLong() }
    var (bx, by) = reader.readLine().split(" ").map { it.toLong() }
    if (ay == by) {
        var temp = bx
        bx = by
        by = temp
        temp = ax
        ax = ay
        ay = temp
    }
    if (ay < by) {
        val temp = ay
        ay = by
        by = temp
    }
    var cnt = (N + 1L) * N / 2L
    cnt *= cnt
    if (ax == bx) {
        val dotCnt = min(N, ay) - max(-1L, by)
        if (dotCnt > 1L) {
            cnt += (N + 1L) * dotCnt * (dotCnt - 1) / 2L
        }
    }
    writer.write("${cnt}\n")
    writer.flush()
}
    