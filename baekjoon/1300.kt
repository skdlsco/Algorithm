package `1300`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toLong()
    val K = reader.readLine().toLong()
    var left = 1L
    var right = N * N
    while (left < right) {
        val mid = (left + right) / 2
        var cnt = 0L
        for (i in 1..N) {
            cnt += minOf(mid / i, N)
        }
        if (cnt < K) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    writer.write("${left}\n")
    writer.flush()
}
    