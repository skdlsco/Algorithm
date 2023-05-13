package `15922`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    var (start, end) = reader.readLine().split(" ").map { it.toInt() }
    var sum = 0

    repeat(N - 1) {
        val (s, e) = reader.readLine().split(" ").map { it.toInt() }
        // 아예 안겹치는 경우
        if (s > end) {
            sum += end - start
            start = s
            end = e
        }
        if (end < e)
            end = e
    }
    sum += end - start
    writer.write("${sum}\n")
    writer.flush()
}